package com.mongo.springboot;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mongodb.client.model.Filters.eq;

@RestController
@RequestMapping("v1/person")
public class PersonController {

//    @Autowired
//    PaintRepo paintRepo;

    @GetMapping("/all")
    ResponseEntity<?> getAll(){


        ConnectionString connectionString = new ConnectionString("mongodb+srv://root:root@cluster0.oesnvye.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("testdb");

        MongoCollection<Document> coll1 = database.getCollection("paint_purchases");

        Bson equalComparison = eq("qty", 5);
        coll1.find(equalComparison).forEach(doc -> System.out.println(doc.toJson()));

        MongoCollection<Document> coll2 = database.getCollection("testcollection");
        Document doc = coll2.find().first();
        System.out.println(doc.toJson());

//        System.out.println(paintRepo.findAll());


        return new ResponseEntity("ok",HttpStatus.OK);
    }


}
