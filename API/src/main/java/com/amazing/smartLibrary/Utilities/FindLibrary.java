package com.amazing.smartLibrary.Utilities;

import com.amazing.smartLibrary.Algos.CoordinateDistance;
import com.amazing.smartLibrary.Models.City;
import com.amazing.smartLibrary.Models.Library;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class FindLibrary {

    private final MongoOperations mongoOperations;

    public FindLibrary(@Qualifier("getMongoOperations") MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @RequestMapping("/libraries-in-city")
    public List<Library> librariesInCity(@RequestParam("city") String city) {
        List<Library> libraries = new ArrayList<>();
        Query query = new Query(Criteria.where("cityName").is(city));
        City current = mongoOperations.findOne(query, City.class);
        if(current == null) return null;
        for(int i = 0; i < current.getLibrariesInCity().size(); ++i) {
            String library = current.getLibrariesInCity().get(i);
            Query libraryQuery = new Query(Criteria.where("libraryName").is(library));
            libraries.add(mongoOperations.findOne(libraryQuery, Library.class));
        }
        return libraries;
    }

    @RequestMapping("/nearby-library")
    public List<Library> nearbyLibrary(@RequestParam("lat") String lat1String,
                                       @RequestParam("lon") String lon1String) {
        System.out.println("query made");
        Double lat1 = Double.parseDouble(lat1String), lon1 = Double.parseDouble(lon1String);
        Query query = new Query();
        List<Library> libraries = mongoOperations.find(query, Library.class);
        List<Library> ans = new ArrayList<>();
        Iterator iterator = libraries.iterator();
        while (iterator.hasNext()) {
            Library current = (Library) iterator.next();
            if (CoordinateDistance.getDistance(lat1, lon1, current.getLat(), current.getLon()) <= 10.0) {
                ans.add(current);
            }
        }
        return ans;
    }

}
