package com.spark;

import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;

import java.sql.Date;
import java.util.Properties;

public class CustomerDataProcessor {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("Customer Data Processor")
                .master("local[*]")  // Adjust for cluster mode
                .getOrCreate();

        StructType schema = new StructType()
                .add("Record_Type", DataTypes.StringType)
                .add("Customer_Name", DataTypes.StringType)
                .add("Customer_ID", DataTypes.StringType)
                .add("Open_Date", DataTypes.DateType)
                .add("Last_Consulted_Date", DataTypes.DateType)
                .add("Vaccination_Type", DataTypes.StringType)
                .add("Dr_Name", DataTypes.StringType)
                .add("State", DataTypes.StringType)
                .add("Country", DataTypes.StringType)
                .add("Post_Code", DataTypes.StringType)
                .add("Date_Of_Birth", DataTypes.DateType)
                .add("Is_Active", DataTypes.StringType);

        Dataset<Row> inputData = spark.read()
                .option("header", "false") // No header in input
                .schema(schema)
                .csv("/Users/selvanv/Downloads/customer_data.csv");

        Dataset<Row> processedData = inputData
                .withColumn("Age", functions.year(functions.current_date()).minus(functions.year(functions.to_date(inputData.col("Date_Of_Birth"), "yyyy-MM-dd"))))
                .withColumn("Days_Since_Last_Consulted",
                        functions.datediff(functions.current_date(), functions.to_date(inputData.col("Last_Consulted_Date"), "yyyy-MM-dd")))
                .withColumn("Open_Date", functions.to_date(inputData.col("Open_Date"), "yyyy-MM-dd"))
                .withColumn("Last_Consulted_Date", functions.to_date(inputData.col("Last_Consulted_Date"), "yyyy-MM-dd"))
                .withColumn("Date_Of_Birth", functions.to_date(inputData.col("Date_Of_Birth"), "yyyy-MM-dd"));

        Dataset<Row> filteredData = processedData.filter("Days_Since_Last_Consulted > 30");

        Dataset<Row> indiaData = filteredData.filter("Country = 'IND'");
        Dataset<Row> usaData = filteredData.filter("Country = 'USA'");
        Dataset<Row> ausData = filteredData.filter("Country = 'AUS'");

        String jdbcUrl = "jdbc:mysql://localhost:3306/test";
        Properties dbProperties = new Properties();
        dbProperties.put("driver", "com.mysql.cj.jdbc.Driver");
        dbProperties.put("user", "root");
        dbProperties.put("password", "root");

        indiaData.write().mode("overwrite").jdbc(jdbcUrl, "Customer_IND", dbProperties);
        usaData.write().mode("overwrite").jdbc(jdbcUrl, "Customer_USA", dbProperties);
        ausData.write().mode("overwrite").jdbc(jdbcUrl, "Customer_AUS", dbProperties);

        spark.stop();
    }
}
