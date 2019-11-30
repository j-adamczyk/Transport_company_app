package model;

import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Transaction {
    @MongoObjectId
    private String _id;
}
