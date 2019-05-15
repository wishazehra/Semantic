package org.apache.jena.example.helloworld;

import org.apache.jena.base.Sys;
import org.apache.jena.graph.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDF;
import virtuoso.jena.driver.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {



    public static void main(String[] args) {


        String url;
        if(args.length == 0)
            url = "jdbc:virtuoso://localhost:1111"; //this is my connection string. with that port.
        else
            url = args[0];

        VirtGraph set = new VirtGraph (url, "dba", "dba");

        Query sparql = QueryFactory.create("SELECT * FROM <movies_data> WHERE { ?subject ?predicate ?object  } limit 10");




        VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, set);

        ResultSet results = vqe.execSelect(); //this is the result set.


        while (results.hasNext()) {
            QuerySolution result = results.nextSolution();

            RDFNode s = result.get("subject");
            RDFNode p = result.get("predicate");
            RDFNode o = result.get("object");
            System.out.println(" { " + s + " " + p + " " + o + " . }"); //now iam printing.
        }


    }
}
