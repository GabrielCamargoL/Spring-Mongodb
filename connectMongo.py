from pymongo import MongoClient

# pprint library is used to make the output look more pretty

from pprint import pprint

# connect to MongoDB, change the << MONGODB URL >> to reflect your own connection string

client = MongoClient("mongodb://debbuger_duck:<password>@localhost")
db = client.root

# Issue the serverStatus command and print the results

serverStatusResult=db.command("serverStatus")

pprint(serverStatusResult)
