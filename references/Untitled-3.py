Initializing SparkSession: 
>>> from pyspark.sql import SparkSession
>>> spark a SparkSession.builder.appName("Python Spark SQL basic example").config("spark.some.config.option", "some-value").getOrCreate()

Creating DataFrames:
>>> from pyspark.sql.types import*

Infer Schema:
>>> sc = spark.sparkContext
>>> lines = sc.textFile(''people.txt'')
>>> parts = lines.map(lambda l: l.split(","))
>>> people = parts.map(lambda p: Row(nameap[0],ageaint(p[l])))
>>> peopledf = spark.createDataFrame(people)

Specify Schema:
>>> 
people = parts.map(lambda p: Row(name=p[0], age=int(p[1].strip())))
>>>  schemaString = "name age"
>>> fields = [StructField(field_name, StringType(), True) for field_name in schemaString.split()]
>>> schema = StructType(fields)
>>> spark.createDataFrame(people, schema).show()

From Spark Data Sources

JSON:
>>>  df = spark.read.json("customer.json")
>>> df.show()
>>>  df2 = spark.read.load("people.json", format="json")

Parquet files:
>>> df3 = spark.read.load("users.parquet")

TXT files:
>>> df4 = spark.read.text("people.txt")

Filter:
#Filter entries of age, only keep those records of which the values are >24
>>> df.filter(df["age"]>24).show()

Duplicate Values:
>>> df = df.dropDuplicates()

Queries:
>>> from pyspark.sql import functions as F

Select:
>>> df.select("firstName").show()

#Show all entries in firstName column
>>> df.select("firstName","lastName").show()
>>> df.select("firstName",

#Show all entries in firstName, age and type "age",
>>> explode("phoneNumber").alias("contactInfo")).select("contactInfo.type", "firstName", "age")show()
>>> df.select(df["firstName"],df["age"]+ 1) 

#Show all entries in firstName and age, .show() add 1 to the entries of age
>>> df.select(df['age'] > 24).show() #Show all entries where age >24

When:
>>> df.select("firstName", #Show firstName and 0 or 1 depending on age >30 F.when(df.age > 30, 1).otherwise(0)).show()
>>> df[df.firstName.isin("Jane","Boris")] 
#Show firstName if in the given options.collect()

Like:
>>> df.select("firstName", #Show firstName, and lastName is TRUE if lastName is like Smith df.lastName.like("Smith")).show()

Startswith - Endswith:
>>> df.select("firstName", #Show firstName, and TRUE if lastName starts with Sm df.lastName.startswith("Sm")).show()
>>> df.select(df.lastName.endswith("th"))
#Show last names ending in th      .show()