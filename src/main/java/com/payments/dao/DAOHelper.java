package com.payments.dao;

import com.payments.db.JdbcConfig;
import com.payments.entity.Identifiable;
import com.payments.parser.resultset.EntityBuilder;
import com.payments.parser.resultset.EntityBuilderFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;



public class DAOHelper <T extends Identifiable> {  
    
    private enum OperationEnum {
        SELECT, UPDATE;
    }
    
    private enum ResultTypeEnum {
        PARAMS_AND_VALUES, PARAMS, VALUES;
    }
    
    private Class<T> type;
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSetSelect = null;
    private ResultSet resultSetUpdate = null;
    
    public DAOHelper(Class<T> type){
        this.type = type;
    }    
    
    public List<T> executeSelectQuery(String query){         
        List<T> entities = new ArrayList<>();
        try {            
            openDbConnection();
            statement = connection.createStatement(); 
            resultSetSelect = statement.executeQuery(query);
                        
            EntityBuilder builder = EntityBuilderFactory.create(type); 
            
            while (resultSetSelect.next()){
                T entity = (T) builder.build(resultSetSelect);
                entities.add(entity);
            }                       
        } 
        catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } 
        finally {
            closeDbConnection(OperationEnum.SELECT);
        }        
        return entities;
    }
    
    
    public List<Integer> executeUpdateQuery(String query){         
        List<Integer> entitiesId = new ArrayList<>();
        try {            
            openDbConnection();
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            resultSetUpdate = preparedStatement.getGeneratedKeys();     
            
            while (resultSetUpdate.next()){
                int id = resultSetUpdate.getInt(1);
                entitiesId.add(id);
            }                       
        } 
        catch (ClassNotFoundException | SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        } 
        finally {
            closeDbConnection(OperationEnum.UPDATE);
        }        
        return entitiesId;
    }
    
    
    private void openDbConnection() throws ClassNotFoundException, SQLException,
                                           IllegalArgumentException 
    {           
        String driver = JdbcConfig.INSTANCE.getDriver();
        Class.forName(driver);            
        String url = JdbcConfig.INSTANCE.getDbUrl();
        String user = JdbcConfig.INSTANCE.getUser();
        String password = JdbcConfig.INSTANCE.getPassword();
        connection = DriverManager.getConnection(url, user, password);          
    }
    
    
    private void closeDbConnection(OperationEnum operation){
        try {
            if (resultSetSelect != null && operation == OperationEnum.SELECT) {
                resultSetSelect.close();
            }
            if (resultSetUpdate != null && operation == OperationEnum.UPDATE) {
                resultSetUpdate.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public String getTableName(){
        String tableName = null;
        try {
            tableName = (String) type.getField("TABLE_NAME").get(null);            
        }
        catch (IllegalAccessException | NoSuchFieldException e){
            e.printStackTrace();
        }
        return tableName;
    }
    
    
    public String createInsertQuery(T entity){
        String tableName = getTableName();
        String paramsSpliter = ", ";
        String queryParams = entityParamsAndValuesToString(entity, paramsSpliter, ResultTypeEnum.PARAMS);
        String queryValues = entityParamsAndValuesToString(entity, paramsSpliter, ResultTypeEnum.VALUES);
        String query = "INSERT INTO " + tableName + " (" + queryParams 
                     + ") VALUES (" + queryValues + ")";
        return query;
    }
    
    
    public String createUpdateQuery(T entity){
        String tableName = getTableName();
        int entityId = entity.getId();
        String paramsSpliter = ", ";
        String queryParamsAndValues = entityParamsAndValuesToString(entity, paramsSpliter, ResultTypeEnum.PARAMS_AND_VALUES);
        String query = "UPDATE " + tableName + " SET " + queryParamsAndValues 
                     + " WHERE id=" + entityId;
        return query;
    }
    
    
    // TODO: Refactor in future - method has many responsibilities
    private String entityParamsAndValuesToString(T entity, String splitWith, ResultTypeEnum resultType){
        
        Method[] methods = entity.getClass().getMethods();
        List<String> queryParams = new ArrayList<>();
        
        for (Method method : methods){
            
            String methodName = method.getName();
            String methodPrefix = methodName.substring(0, 3); 
            
            Class<?>[] interfaces = method.getReturnType().getInterfaces();
            boolean isCollection = Arrays.asList(interfaces).contains(Collection.class);
            
            if (methodPrefix.equalsIgnoreCase("get") && !isCollection &&
                !methodName.equalsIgnoreCase("getClass"))
            {                
                String fieldName = methodName.substring(3);
                fieldName = splitCamelCase(fieldName).toLowerCase();
                String fieldValue = null;
                try {
                    Object result = method.invoke(entity);
                    fieldValue = String.valueOf(result);
                }
                catch (IllegalAccessException | InvocationTargetException | ClassCastException e){
                    e.printStackTrace();
                }
                
                if (!fieldValue.equalsIgnoreCase("0") && fieldValue != null){
                    if (resultType == ResultTypeEnum.PARAMS_AND_VALUES){
                        queryParams.add(fieldName + "='" + fieldValue + "'");
                    }
                    else if (resultType == ResultTypeEnum.PARAMS){
                        queryParams.add(fieldName);
                    }
                    else if (resultType == ResultTypeEnum.VALUES){
                        queryParams.add("'" + fieldValue + "'");
                    }
                    else {
                        throw new IllegalArgumentException("'" + resultType 
                            + "' is incorrect resultType for method " 
                            + "'entityParamsAndValuesToString'");
                    }                    
                }                 
            }
        }          
        String result = StringUtils.join(queryParams, splitWith);
        return result;
    }
    
    
    public String propertiesToString(Properties conditions, String splitWith){
        List<String> queryParams = new ArrayList<>();
        Enumeration<Object> keys = conditions.keys();
        while (keys.hasMoreElements()){
            String key = (String) keys.nextElement();
            String value = (String) conditions.getProperty(key);
            queryParams.add(key + "='" +value + "'");
        }
        String result = StringUtils.join(queryParams, splitWith);
        return result;
    }
    
    
    private String splitCamelCase(String s) {
        String result = s.replaceAll(
           String.format("%s|%s|%s",
              "(?<=[A-Z])(?=[A-Z][a-z])",
              "(?<=[^A-Z])(?=[A-Z])",
              "(?<=[A-Za-z])(?=[^A-Za-z])"
           ),
           "_"
        );
        return result;
    }
}
