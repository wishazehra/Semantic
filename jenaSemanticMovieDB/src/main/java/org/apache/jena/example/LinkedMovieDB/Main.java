package org.apache.jena.example.LinkedMovieDB;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;

import virtuoso.jena.driver.*;

public class Main {

    public static void main(String[] args) {

        String url;
        if(args.length == 0)
            url = "jdbc:virtuoso://localhost:1111"; //connection with port.
        else
            url = args[0];

        VirtGraph set = new VirtGraph (url, "dba", "dba"); //connection established

        Query sparql = QueryFactory.create("SELECT * FROM <movies> WHERE { ?s ?p ?o  } limit 50"); //SPARQL query


        VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, set);

        ResultSet results = vqe.execSelect(); //result set.


        while (results.hasNext()) {
            QuerySolution result = results.nextSolution();

            RDFNode sub = result.get("s");
            RDFNode pre = result.get("p");
            RDFNode obj = result.get("o");
            System.out.println(" { " + sub + " " + pre + " " + obj + " . }");
        }

    }
}
