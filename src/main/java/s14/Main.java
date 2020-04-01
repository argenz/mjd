package s14;

import java.util.List;
import java.util.Optional;

import s14.dao.Coder;
import s14.dao.CoderDao;

public class Main {
    public static void main(String[] args) {
        CoderDao cd = new CoderDao(); 

        // create a new coder
        Coder tom = new Coder(501, "Tom", "Jones", 2000);
        cd.save(tom);

        // get a coder
        Optional<Coder> opt = cd.get(501);    //metti nell'optional per gestire i null.
        if (opt.isPresent()) {
            System.out.println("Coder 501: " + opt.get());

            Coder coder = opt.get();
            coder.setSalary(coder.getSalary() * 2);
            cd.update(coder);
            

        } else {
            System.out.println("Unexpected! Can't get coder 501");
        }

        // rename a coder
        tom.setLastName("Bombadil");
        cd.update(tom);

        opt = cd.get(501);
        if (opt.isPresent()) {
            System.out.println("Coder 501: " + opt.get());
        } else {
            System.out.println("Unexpected! Can't get coder 501");
        }

        // delete a coder
        cd.delete(501);

        opt = cd.get(501);
        if (opt.isPresent()) {
            System.out.println("Unexpected! Coder 501 still alive: " + opt.get());
        } else {
            System.out.println("Coder 501 is no more");
        }

        // get all coders
        List<Coder> coders = cd.getAll();
        System.out.println("Coders: " + coders);
    }
}
