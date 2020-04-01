package s14.dao;

import java.util.List;
import java.util.Optional;

public class MainRegions {

	public static void main(String[] args) {
		RegionDao rd = new RegionDao(); 

        // create a new region
        Region oceania = new Region(5, "Oceania");
        rd.save(oceania);

        // get a region
        Optional<Region> opt = rd.get(3);    
        if (opt.isPresent()) {
            System.out.println("Region 3: " + opt.get());

//            Region region = opt.get();
//            region.setRegionName("New" + region.getRegionName());
//            rd.update(region);
//            
           
        } else {
            System.out.println("Unexpected! Can't get Region 3");
        }

        // rename a region
        oceania.setRegionName("Oceania");
        rd.update(oceania);

        opt = rd.get(5);
        if (opt.isPresent()) {
            System.out.println("Region 5: " + opt.get());
        } else {
            System.out.println("Unexpected! Can't get Region 5");
        }
//
//        // delete a region
//        rd.delete(5);
//
//        opt = rd.get(5);
//        if (opt.isPresent()) {
//            System.out.println("Unexpected! Region 3 still exists: " + opt.get());
//        } else {
//            System.out.println("Region 3 has been estinguished");
//        }
//
//        // get all regions
//        List<Region> regions = rd.getAll();
//        System.out.println("Regions: " + regions);
   }

}


