package com.saras.etl.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AssessmentUtility {
    public static final Logger logger = LoggerFactory.getLogger(AssessmentUtility.class);

   public static <T> List<T> getRandomValuesAsList(List<T> userList, int limit){
       if(limit > userList.size()){
           logger.error("Limit is larger then provided list");
           throw new IllegalArgumentException("Limit is larger then provided list");
       }
       Random random = new Random();
       Set<Integer> indices = new HashSet<>();

       while (indices.size() < limit){
           indices.add(random.nextInt(userList.size()));
       }
       List<T> randomList = new ArrayList<>();
       for(int index : indices){
           randomList.add(userList.get(index));
       }
       logger.debug("getRandomValuesAsList return list");
       return randomList;
   }
}
