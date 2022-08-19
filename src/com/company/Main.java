package com.company;
import java.io.File;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // write your code here
        ArrayList<State> states = new ArrayList<>();
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Partition> partitions = new ArrayList<>();
        addState(states);
        addCity("./csvFiles/cities.csv", cities, states);
        int counter;
        //partition("./csvFiles/nws-central.csv",partitions, cities, states );
        System.out.println("Enter a number to decide what information you would like to know about: \n (1) State and Region Information\n " +
                "(2) Radar Site Information \n (3) Population Coverage Information \n (4) Quit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        while (choice != 4) {
            switch (choice) {
                case (1):
                    System.out.println("Pick from one of the following: \n " +
                            "(1) Find the regions with the smallest and largest population\n" +
                            "(2)Find the regions with the smallest and largest surface area \n " +
                            "(3) Find the states in a given region with the smallest and largest populations \n" +
                            "(4) Find the states in a given region with the smallest and largest surface areas");
                    choice = in.nextInt();
                    switch (choice) {
                        case (1):

                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);

                            int biggest = 0;
                            int smallest;
                            int currentBig = 0;
                            int currentSmall = 999999999;
                            String regionNameSmall = " ";
                            String regionNameLarge = " ";
                            //stateNameSmall = String.valueOf(partitions.get(0).getRadars(0).getState());;


                            for (int j = 0; j < partitions.size(); j++) {
                                biggest = 0;
                                // stateNameLarge = String.valueOf(partitions.get(0).getRadars(0).getState());
                                smallest = 0;
                                //stateNameSmall = String.valueOf(partitions.get(0).getRadars(0).getState());;
                                for (int i = 0; i < partitions.get(j).getRadarSize(); i++) {
                                    if (partitions.get(j).getRadars(i).getState().getPopulation() > biggest) {
                                        //stateNameLarge = partitions.get(j).getRadars(i).getState().toString();
                                        biggest += partitions.get(j).getRadars(i).getState().getPopulation();
                                    }

                                    if (partitions.get(j).getRadars(i).getState().getPopulation() > smallest) {
                                        //stateNameSmall = partitions.get(j).getRadars(i).getState().toString();
                                        smallest += partitions.get(j).getRadars(i).getState().getPopulation();

                                    }
                                }
                                if (biggest > currentBig) {
                                    currentBig = biggest;
                                    regionNameLarge = partitions.get(j).getName();
                                }
                                if (smallest < currentSmall) {
                                    currentSmall = smallest;
                                    regionNameSmall = partitions.get(j).getName();

                                }
                            }
                            System.out.println("Most populated region: " + regionNameLarge + " population: " + currentBig);
                            System.out.println("Least populated region: " + regionNameSmall + " population: " + currentSmall);
                            partitions.clear();
                            break;

                        case (2):
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);

                            String stateNameLargeSA = " ";
                            String stateNameSmallSA = " ";
                            int biggestSA = 0;
                            int smallestSA = 999999999;
                            String regionNameBigSA = " ";
                            String regionNameSmallSA = " ";
                            for (int i = 0; i < partitions.size(); i++) {
                                int biggestSALarge = 0;
                                int smallestSASmall = 0;
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    if (partitions.get(i).getRadars(j).getState().getSurfaceArea() > biggestSA) {
                                        biggestSALarge += partitions.get(i).getRadars(j).getState().getSurfaceArea();
                                        //stateNameLargeSA = partitions.get(i).getRadars(j).getState().toString();;
                                    }
                                    if (partitions.get(i).getRadars(j).getState().getSurfaceArea() < smallestSA) {
                                        smallestSASmall += partitions.get(i).getRadars(j).getState().getSurfaceArea();
                                        stateNameSmallSA = partitions.get(i).getRadars(j).getState().toString();
                                        ;
                                    }
                                }
                                if (biggestSALarge > biggestSA) {
                                    biggestSA = biggestSALarge;
                                    regionNameBigSA = partitions.get(i).getName();

                                }
                                if (smallestSASmall < smallestSA) {
                                    smallestSA = smallestSASmall;
                                    regionNameSmallSA = partitions.get(i).getName();
                                }
                                //System.out.println(stateNameSmallSA + smallestSA);
                            }
                            System.out.println("Region with largest surfaceArea: " + regionNameBigSA + " Surface Area:  " + biggestSA);
                            System.out.println("Region with smallest surfaceArea: " + regionNameSmallSA + " Surface Area: " + smallestSA);
                            partitions.clear();
                            break;
                        case (3):
                            in.nextLine();
                            System.out.println("Choose a region: Central, Eastern, Southern, Western");
                            String regionChoicePop = in.nextLine();
                            if (regionChoicePop.equalsIgnoreCase("Central") || regionChoicePop.equalsIgnoreCase("Eastern") || regionChoicePop.equalsIgnoreCase("Southern") || regionChoicePop.equalsIgnoreCase("Western")) {
                                partition("./csvFiles/nws-" + regionChoicePop.toLowerCase(Locale.ROOT) + ".csv", partitions, cities, states);
                            } else {
                                System.out.println("Invalid region");
                            }
                            String stateNameLarge = " ";
                            String stateNameSmall = " ";
                            int biggestStatePop = 0;
                            int smallestStatePop = 999999999;
                            int popEachState = 0;
                            int totalPopSmall = 0;
                            String stateNamePopLarge = " ";
                            String stateNamePopSmall = " ";
                            for (int i = 0; i < partitions.get(0).getRadarSize(); i++) {
                                popEachState = partitions.get(0).getRadars(i).getState().getPopulation();
                                if (popEachState > biggestStatePop) {
                                    biggestStatePop = popEachState;
                                    stateNamePopLarge = partitions.get(0).getRadars(i).getState().toString();
                                }
                                if (popEachState < smallestStatePop) {
                                    smallestStatePop = popEachState;
                                    stateNamePopSmall = partitions.get(0).getRadars(i).getState().toString();
                                }
                            }
                            System.out.println("Largest populated state in  " + regionChoicePop + " region: " + stateNamePopLarge + " population: " + biggestStatePop);
                            System.out.println("Smallest populated state in  " + regionChoicePop + " region: " + stateNamePopSmall + " population: " + smallestStatePop);
                            System.out.println();
                            partitions.clear();
                            break;
                        case (4):
                            in.nextLine();
                            System.out.println("Choose a region: Central, Eastern, Southern, Western");
                            String regionChoiceSA = in.nextLine();
                            if (regionChoiceSA.equalsIgnoreCase("Central") || regionChoiceSA.equalsIgnoreCase("Eastern") || regionChoiceSA.equalsIgnoreCase("Southern") || regionChoiceSA.equalsIgnoreCase("Western")) {
                                partition("./csvFiles/nws-" + regionChoiceSA.toLowerCase(Locale.ROOT) + ".csv", partitions, cities, states);
                            } else {
                                System.out.println("Invalid region");
                            }
                            String stateNameBiggestSA = " ";
                            String stateNameSmallestSA = " ";
                            int biggestSAInRegion = 0;
                            int smallestSAInRegion = 999999999;
                            int SAEachState = 0;
                            for (int i = 0; i < partitions.get(0).getRadarSize(); i++) {
                                SAEachState = partitions.get(0).getRadars(i).getState().getSurfaceArea();
                                if (SAEachState > biggestSAInRegion) {
                                    biggestSAInRegion = SAEachState;
                                    stateNameBiggestSA = partitions.get(0).getRadars(i).getState().toString();
                                }
                                if (SAEachState < smallestSAInRegion) {
                                    smallestSAInRegion = SAEachState;
                                    stateNameSmallestSA = partitions.get(0).getRadars(i).getState().toString();
                                }
                            }
                            System.out.println("State in region " + regionChoiceSA + " with most surface area is " + stateNameBiggestSA + " with a surface area of : " + biggestSAInRegion);
                            System.out.println("State in region " + regionChoiceSA + " with least surface area is " + stateNameSmallestSA + " with a surface area of : " + smallestSAInRegion);
                            System.out.println();
                            partitions.clear();
                            break;
                    }
                    break;

                case (2):
                    System.out.println("Pick from one of the following: \n " +
                            "(1) Find the states with the smallest and largest number of radar sites\n" +
                            "(2) Find the regions with the smallest and largest number of radar sites\n " +
                            "(3) Print a list of all radar sites in a given state\n" +
                            "(4)  Print a list of all radar sites in a given region that are within radar range (143" +
                            "miles) of a radar site in a different city");
                    int choiceTwo = in.nextInt();
                    switch (choiceTwo) {
                        case (1):
                            in.nextLine();
                            int largestNumRadarSites = 0;
                            int countBig = 0;
                            int countSmall = 0;

                            String comparativeString = " ";
                            String stateNameRadar = " ";
                            ArrayList<String> stateList = new ArrayList<>();
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            partition("./csvFiles/dod-all.csv", partitions, cities, states);
                            partition("./csvFiles/dot-all.csv", partitions, cities, states);


                            for (int i = 0; i < partitions.size(); i++) {
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    stateNameRadar = partitions.get(i).getRadars(j).getState().getName();
                                    stateList.add(stateNameRadar);

                                }

                            }
                            String mostRadarSitesState = " ";
                            String smallestNumRadarSites = " ";
                            int biggest = 0;
                            int smallest = 999999999;
                            for (int i = 0; i < stateList.size(); i++) {
                                String stateNameElement = stateList.get(i);
                                countBig = 1;
                                countSmall = stateList.size();
                                countBig++;
                                for (int j = i + 1; j < stateList.size(); j++) {
                                    if (stateNameElement.equals(stateList.get(j))) {
                                        countBig++;

                                    } else {
                                        countSmall--;
                                    }

                                    if (countBig > biggest) {
                                        biggest = countBig;
                                        mostRadarSitesState = stateList.get(i);

                                    }
                                    if (countSmall < smallest) {
                                        smallest = countSmall;
                                        smallestNumRadarSites = stateList.get(i);
                                    }
                                }
                            }
                            stateList.clear();
                            System.out.println(mostRadarSitesState + " has the most radar sites with " + biggest + " sites ");
                            System.out.println(smallestNumRadarSites + " has the least radar sites with " + smallest + " sites ");
                            partitions.clear();
                            break;

                        case (2):
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            int regionalBiggest = 0;
                            int regionalSmallest = 99999999;
                            int regionalRadarTotal;
                            String regionalBigName = " ";
                            String regionalSmallName = " ";
                            for (int i = 0; i < partitions.size(); i++) {
                                regionalRadarTotal = partitions.get(i).getRadarSize();
                                if (regionalRadarTotal > regionalBiggest) {
                                    regionalBiggest = regionalRadarTotal;
                                    regionalBigName = partitions.get(i).getName();
                                }
                                if (regionalRadarTotal < regionalSmallest) {
                                    regionalSmallest = regionalRadarTotal;
                                    regionalSmallName = partitions.get(i).getName();

                                }
                            }
                            System.out.println("Region with largest number of sites: " + regionalBigName + " with a total of " + regionalBiggest + " sites");
                            System.out.println("Region with smallest number of sites: " + regionalSmallName + " with a total of " + regionalSmallest + " sites");
                            partitions.clear();
                            break;
                        case (3):
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            partition("./csvFiles/dod-all.csv", partitions, cities, states);
                            partition("./csvFiles/dot-all.csv", partitions, cities, states);
                            System.out.println("Enter a state: ");
                            in.nextLine();
                            String userChoice = in.nextLine();
                            counter = 0;
                            int resetCounter = 0;
                            String radarSites = " ";
                            System.out.print(userChoice + "'s Radar Sites: ");
                            for (int i = 0; i < partitions.size(); i++) {
                                counter = resetCounter;
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    stateNameRadar = partitions.get(i).getRadars(j).getState().getName();
                                    if (userChoice.equalsIgnoreCase((stateNameRadar))) {
                                        radarSites = partitions.get(i).getRadars(j).getICOA();
                                        System.out.print(radarSites + " ");

                                    }

                                }

                            }
                            partitions.clear();
                            System.out.println();
                            break;
                        case (4):
                            in.nextLine();
                            System.out.println("Choose a region: Central, Eastern, Southern, Western");
                            String regionChoice = in.nextLine();
                            City newCity = new City(" ");
                            if (regionChoice.equalsIgnoreCase("Central") || regionChoice.equalsIgnoreCase("Eastern") || regionChoice.equalsIgnoreCase("Southern") || regionChoice.equalsIgnoreCase("Western")) {
                                partition("./csvFiles/nws-" + regionChoice.toLowerCase(Locale.ROOT) + ".csv", partitions, cities, states);
                                partition("./csvFiles/dot-all.csv", partitions, cities, states);
                                partition("./csvFiles/dod-all.csv", partitions, cities, states);

                            } else {
                                System.out.println("Invalid region");
                            }
                            System.out.println("Select a city in this region: ");
                            for (int i = 0; i < partitions.size(); i++) {
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    System.out.println(partitions.get(i).getRadars(j).getCity().getName() + " ");
                                }
                                System.out.println();
                            }
                            String regionChoiceDistance = in.nextLine();
                            double latitude;
                            double longitude;

                            for (int i = 0; i < partitions.size(); i++) {
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    if (partitions.get(i).getRadars(j).getCity().toString().contains(newCity.getName().toUpperCase(Locale.ROOT))) {
                                        newCity = new City(regionChoiceDistance, partitions.get(i).getRadars(j).getCity().getLatitude(), partitions.get(i).getRadars(j).getCity().getLongitude());
                                        break;
                                    }
                                }
                            }
                            for (int j = 0; j < partitions.size(); j++) {
                                for (int i = 0; i < partitions.get(j).getRadarSize(); i++) {
                                    double distance = partitions.get(j).getRadars(i).getCity().distance(newCity);
                                    if (distance < 143) {
                                        System.out.println(partitions.get(j).getRadars(i).getCity().getName() + " " + partitions.get(j).getRadars(i).getICOA());
                                    }
                                }
                            }
                            partitions.clear();
                            break;
                    }
                    break;


                case (3):
                    in.nextLine();
                    System.out.println("Pick from one of the following: \n " +
                            "(1) Find the regions with the smallest and largest ratio of population to radar stations\n" +
                            "(2) Find the regions with the smallest and largest ratio of surface area to radar\n" +
                            "stations\n " +
                            "(3) Find the states with the smallest and largest ratio of population to radar stations\n" +
                            "(4) Find the states with the smallest and largest ratio of surface area to radar stations");
                    int userChoiceThree = in.nextInt();
                    switch (userChoiceThree) {
                        case (1):
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            int totalPopulation = 0;
                            int biggestRatio = 0;
                            int smallestRatio = 0;
                            int comparativeBigRatio = 0;
                            int comparativeSmallRatio = 999999999;
                            String regionalNameBigRatio = " ";
                            String regionalNameSmallRatio = " ";

                            for (int i = 0; i < partitions.size(); i++) {

                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    totalPopulation += partitions.get(i).getRadars(j).getState().getPopulation();
                                }
                                if (totalPopulation / partitions.get(i).getRadarSize() > comparativeBigRatio) {
                                    comparativeBigRatio = totalPopulation / partitions.get(i).getRadarSize();
                                    regionalNameBigRatio = partitions.get(i).getName();

                                }
                                if (totalPopulation / partitions.get(i).getRadarSize() < comparativeSmallRatio) {
                                    comparativeSmallRatio = totalPopulation / partitions.get(i).getRadarSize();
                                    regionalNameSmallRatio = partitions.get(i).getName();
                                }
                            }
                            System.out.println("Largest ratio: " + regionalNameBigRatio + " " + comparativeBigRatio);
                            System.out.println("Smallest ratio: " + regionalNameSmallRatio + " " + comparativeSmallRatio);
                            partitions.clear();
                            break;
                        case (2):
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            int totalSA = 0;
                            int biggestRatioSA = 0;
                            int smallestRatioSA = 0;
                            int comparativeBigRatioSA = 0;
                            int comparativeSmallRatioSA = 999999999;
                            String regionalNameBigRatioSA = " ";
                            String regionalNameSmallRatioSA = " ";

                            for (int i = 0; i < partitions.size(); i++) {

                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    totalSA += partitions.get(i).getRadars(j).getState().getSurfaceArea();
                                }
                                if (totalSA / partitions.get(i).getRadarSize() > comparativeBigRatioSA) {
                                    comparativeBigRatioSA = totalSA / partitions.get(i).getRadarSize();
                                    regionalNameBigRatioSA = partitions.get(i).getName();
                                }
                                if (totalSA / partitions.get(i).getRadarSize() < comparativeSmallRatioSA) {
                                    comparativeSmallRatioSA = totalSA / partitions.get(i).getRadarSize();
                                    regionalNameSmallRatioSA = partitions.get(i).getName();
                                }

                            }
                            System.out.println("Largest ratio: " + regionalNameBigRatioSA + " " + comparativeBigRatioSA);
                            System.out.println("Smallest ratio: " + regionalNameSmallRatioSA + " " + comparativeSmallRatioSA);
                            partitions.clear();
                            break;
                        case (3):
                            in.nextLine();
                            int largestNumRadarSites = 0;
                            int countBig = 0;
                            int countSmall = 0;

                            String comparativeString = " ";
                            String stateNameRadar = " ";
                            ArrayList<String> stateList = new ArrayList<>();
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            partition("./csvFiles/dod-all.csv", partitions, cities, states);
                            partition("./csvFiles/dot-all.csv", partitions, cities, states);


                            for (int i = 0; i < partitions.size(); i++) {
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    stateNameRadar = partitions.get(i).getRadars(j).getState().getName();
                                    stateList.add(stateNameRadar);

                                }

                            }
                            String mostRadarSitesState = " ";
                            String smallestNumRadarSites = " ";
                            int biggest = 0;
                            int smallest = 999999999;
                            for (int i = 0; i < stateList.size(); i++) {
                                String stateNameElement = stateList.get(i);
                                countBig = 1;
                                countSmall = stateList.size();
                                countBig++;
                                for (int j = i + 1; j < stateList.size(); j++) {
                                    if (stateNameElement.equals(stateList.get(j))) {
                                        countBig++;

                                    } else {
                                        countSmall--;
                                    }

                                    if (countBig > biggest) {
                                        biggest = countBig;
                                        mostRadarSitesState = stateList.get(i);

                                    }
                                    if (countSmall < smallest) {
                                        smallest = countSmall;
                                        smallestNumRadarSites = stateList.get(i);
                                    }
                                }
                            }
                            int population = 0;
                            int biggestRatioPop = 0;
                            int smallestRatioStatePop = 999999999;
                            String stateBigRatioPop = " ";
                            String stateSmallRatioPop = " ";
                            for(int i = 0; i < partitions.size(); i++){
                                for(int j = 0; j < partitions.get(i).getRadarSize(); j++){
                                    population = partitions.get(i).getRadars(j).getState().getPopulation();
                                    if(population / smallest > biggestRatioPop){
                                        biggestRatioPop = population / smallest;
                                        stateBigRatioPop = partitions.get(i).getRadars(j).getState().getName();
                                    }
                                    if(population / biggest < smallestRatioStatePop ){
                                        smallestRatioStatePop = population / biggest;
                                        stateSmallRatioPop = partitions.get(i).getRadars(j).getState().getName();
                                    }
                                }
                            }
                            stateList.clear();
                            System.out.println(stateBigRatioPop + " " +  biggestRatioPop);
                            System.out.println(stateSmallRatioPop  + " " + smallestRatioStatePop);
                            partitions.clear();
                            break;
                        case(4):
                            in.nextLine();
                            int countBigSA = 0;
                            int countSmallSA = 0;

                            String comparativeStringSA = " ";
                            String stateNameRadarSA = " ";
                            ArrayList<String> stateListSA = new ArrayList<>();
                            partition("./csvFiles/nws-central.csv", partitions, cities, states);
                            partition("./csvFiles/nws-eastern.csv", partitions, cities, states);
                            partition("./csvFiles/nws-western.csv", partitions, cities, states);
                            partition("./csvFiles/nws-southern.csv", partitions, cities, states);
                            partition("./csvFiles/dod-all.csv", partitions, cities, states);
                            partition("./csvFiles/dot-all.csv", partitions, cities, states);


                            for (int i = 0; i < partitions.size(); i++) {
                                for (int j = 0; j < partitions.get(i).getRadarSize(); j++) {
                                    stateNameRadarSA = partitions.get(i).getRadars(j).getState().getName();
                                    stateListSA.add(stateNameRadarSA);

                                }

                            }
                            String mostRadarSitesStateSA = " ";
                            String smallestNumRadarSitesSA = " ";
                            int biggestSA = 0;
                            int smallestSA = 999999999;
                            for (int i = 0; i < stateListSA.size(); i++) {
                                String stateNameElement = stateListSA.get(i);
                                countBigSA = 1;
                                countSmallSA = stateListSA.size();
                                countBigSA++;
                                for (int j = i + 1; j < stateListSA.size(); j++) {
                                    if (stateNameElement.equals(stateListSA.get(j))) {
                                        countBigSA++;

                                    } else {
                                        countSmallSA--;
                                    }

                                    if (countBigSA > biggestSA) {
                                        biggestSA = countBigSA;
                                        mostRadarSitesStateSA = stateListSA.get(i);

                                    }
                                    if (countSmallSA < smallestSA) {
                                        smallestSA = countSmallSA;
                                        smallestNumRadarSitesSA = stateListSA.get(i);
                                    }
                                }
                            }
                            int populationSA = 0;
                            int biggestRatioStateSA = 0;
                            int smallestRatioStateSA = 999999999;
                            String stateBigRatioSA = " ";
                            String stateSmallRatioSA = " ";
                            for(int i = 0; i < partitions.size(); i++){
                                for(int j = 0; j < partitions.get(i).getRadarSize(); j++){
                                    populationSA = partitions.get(i).getRadars(j).getState().getSurfaceArea();
                                    if(populationSA / smallestSA > biggestRatioStateSA){
                                        biggestRatioStateSA = populationSA / smallestSA;
                                        stateBigRatioSA = partitions.get(i).getRadars(j).getState().getName();
                                    }
                                    if(populationSA / biggestSA < smallestRatioStateSA ){
                                        smallestRatioStateSA = populationSA / biggestSA;
                                        stateSmallRatioSA = partitions.get(i).getRadars(j).getState().getName();
                                    }
                                }
                            }
                            stateListSA.clear();
                            System.out.println(stateBigRatioSA + " " +  biggestRatioStateSA);
                            System.out.println(stateSmallRatioSA  + " " + smallestRatioStateSA);
                            partitions.clear();
                            break;

                            }

                            break;

                    }
            System.out.println("Enter a number to decide what information you would like to know about: \n (1) State and Region Information\n " +
                    "(2) Radar Site Information \n (3) Population Coverage Information \n (4) Quit");

            choice = in.nextInt();
            System.out.println();
            }
        }







    Scanner input = new Scanner(System.in);
    //ArrayList<State> states = new ArrayList<State>();
    ArrayList<Radar> radars = new ArrayList<>();


    public static void addState(ArrayList<State> states) {
       // File citiesFile = new File("cities.csv");
        File stateAbrev = new File("./csvFiles/state-abbreviation.csv");
        File statePop = new File("./csvFiles/state-population.csv");
        File surfaceAreaFile = new File("./csvFiles/state-surfacearea.csv");
        State state = null;
        String[] abrevColumn;
        String[] abrev;
        String stateName = " ";
        String surfaceAreaState = " ";
        String stateNameComparision = "George";
        ArrayList<String> surfaceAreaStateList = new ArrayList<>();
        ArrayList<String> stateNameList = new ArrayList<>();
        try  {
            Scanner in = new Scanner(stateAbrev);
            Scanner input = new Scanner(statePop);
            Scanner input1 = new Scanner(surfaceAreaFile);
            while (in.hasNextLine() && input.hasNextLine() && input1.hasNextLine()) {
                abrevColumn = in.nextLine().split(",");
                String[] populationRow = input.nextLine().split(",");
                String[] surfaceAreaRow = input1.nextLine().split(",");
                if (!abrevColumn[1].equalsIgnoreCase("Abbreviation") || !populationRow[1].equals("POPULATION") || !surfaceAreaRow[1].equals("Surface Area (Sq. Mi)")) {
                    stateName = abrevColumn[0];
                    String abbreviation = abrevColumn[1];
                    String population = populationRow[1];
                    surfaceAreaState = surfaceAreaRow[0];
                    String surfaceArea = surfaceAreaRow[1];
                    state = new State(stateName, abbreviation);
                    states.add(state);
                    state.setPopulation(Integer.parseInt(population));
                    state.setSurfaceArea(Integer.parseInt(surfaceArea));
                }
            }
            in.close();
            input.close();
            input1.close();



        } catch (Exception e) {
            System.out.println("There was an error at" + e);
        }
    }
    public static void addCity(String filename,ArrayList<City> cities, ArrayList<State> states ) {
        String latitude = " ";
        String longitude= " ";
        double latitude1 = 0;
        double longitude1 = 0;
        double latitude2 = 0;
        double longitude2 = 0;
        String city = " ";
        City newCity;
        String stateName = "";
        State state = new State();
        String cityAndState;

        try {
            Scanner in = new Scanner(new File(filename));
            while (in.hasNextLine()) {
                String[] cityRow = in.nextLine().split(",|\\n");

                if (!cityRow[0].equalsIgnoreCase("City")) {
                    city = cityRow[0];
                    stateName = cityRow[1];
                    cityAndState = cityRow[0] + ", " + cityRow[1];

                    String longAndLat = cityRow[2];

                    String[] split = longAndLat.split("\\s");
                    String[] splitBoth = new String[2];
                    if (!longAndLat.equalsIgnoreCase("Location")) {
                        for (int i = 0; i < splitBoth.length; i++) {
                            splitBoth = longAndLat.split(" ");
                            if (i == 0) {
                                latitude = splitBoth[0];

                            } else {
                                longitude = splitBoth[1];
                            }
                        }
                        String latitudeRemovalChar = latitude.substring(0, latitude.length() - 1);
                        String longitudeRemovalChar = longitude.substring(0, longitude.length() - 1);
                        latitude1 = Double.parseDouble(latitudeRemovalChar);
                        longitude1 = Double.parseDouble(longitudeRemovalChar);
                        if (latitude.charAt(latitude.length() - 1) == 'S') {
                            latitude2 = -latitude1;
                        } else {
                            latitude2 = Double.parseDouble(latitudeRemovalChar);
                        }
                        if(longitude.charAt(longitude.length() -1) == 'W'){
                            longitude2 = -longitude1;
                        }
                        else{
                            longitude2 = Double.parseDouble(longitudeRemovalChar);
                        }
                        newCity = new City(cityAndState);
                        newCity.setLatitude(latitude2);
                        newCity.setLongitude(longitude2);
                        cities.add(newCity);
                        state  = new State(stateName);
                        state.addCity(newCity);
                    }
                }
            }
            in.close();



        }
        catch(Exception e) {
            System.out.println("There was an error at" + e);
        }


    }

    public static void partition(String filename, ArrayList<Partition> partitions, ArrayList<City> cities, ArrayList<State> states){
        Partition partition = new Partition("");
        Region region = new Region(" ");
        Radar radar = new Radar();
        if(filename.contains("nws")) {
            File regionFile = new File(filename);

            if (filename.contains("central")) {
                partition = new Region("central");
            }
            else if (filename.contains("eastern")) {
                partition = new Region("eastern");
            }
            else if (filename.contains("western")) {
                partition = new Region("western");
            }
            else {
                partition = new Region("southern");
            }
        }
        else {
            partition = new Partition("partition");
            }
        partitions.add(partition);
        try{
            Scanner in = new Scanner(new File(filename));
            String nexrad = " ";
            String ICAO = " ";
            String city = " ";
            String state;
            City cityInRadar =  new City(" ");
            State stateInRadar = new State("");
            String[] latAndLongSplit;
            String[] cities1;
            String[] cityString = { " "};
            int j = 0;
            while(in.hasNextLine()){
                String[] eachLine = in.nextLine().split(",");
                if (!eachLine[0].equalsIgnoreCase("NEXRAD SYSTEM") || !eachLine[1].equalsIgnoreCase("ICAO")) {
                    nexrad = eachLine[0];
                    ICAO = eachLine[1];
                    city = eachLine[2];
                    state = eachLine[3];
                    radar = new Radar(ICAO);
                    cityInRadar = new City(city);

                    for(int i = 0; i < cities.size(); i++) {
                        if (cities.get(i).toString().toUpperCase(Locale.ROOT).contains(city)) {
                            cityInRadar.setLatitude(cities.get(i).getLatitude());
                            cityInRadar.setLongitude(cities.get(i).getLongitude());
                        }
                    }


                    for(int i = 0; i < states.size(); i++){
                        if(states.get(i).toString().contains(state)){
                            stateInRadar = new State(states.get(i).getName(), state, states.get(i).getPopulation(), states.get(i).getSurfaceArea());
                        }
                    }
                    radar.setCity(cityInRadar);
                    radar.setState(stateInRadar);
                    partition.addRadar(radar);


                    if(filename.contains("csv")){
                        region.addStates(stateInRadar);
                    }

                }

            }
            in.close();
        }
        catch(Exception e){
            System.out.println("There was an error at" + e);

        }
    }

}


