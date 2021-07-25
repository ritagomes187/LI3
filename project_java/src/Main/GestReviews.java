package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import Extras.*;
import PackageBusinesses.*;
import PackageReviews.*;
import PackageUsers.*;

public class GestReviews implements IGestReviews{

    private ICatBus businesses;
    private ICatUsers users;
    private ICatRev reviews;
    private IEstatisticas stats;

    public GestReviews(){
        this.businesses = new CatBus();
        this.users = new CatUsers();
        this.reviews = new CatRev();
        this.stats = new Estatisticas();
    }

    public void readBusinessesFile(String fn, boolean def) throws IOException{

        fn = def ? IGestReviews.BUS_PATH : fn;

        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(fn));
        String s;

        while((s = br.readLine()) != null){
            try{
               IBusiness b = Business.parseBusiness(s);
               this.businesses.insere(b);
               count++;
            }
            catch(BadBusiness e){}
        }
        br.close();

        this.stats.set_fn_businesses(fn);
        this.stats.set_businesses(count);
    }

    public void readUsersFile(String fn, boolean def) throws IOException{

        fn = def ? IGestReviews.USR_PATH : fn;

        long count = 0;
        BufferedReader br = new BufferedReader(new FileReader(fn), 150000);
        String s;

        while((s = br.readLine()) != null){
            try{
                IUser u = UserNoFriends.parseUser(s);
                this.users.insere(u);
                count++;
            }
            catch(BadUser e){}
        }
        br.close();
        
        this.stats.set_fn_users(fn);
        this.stats.set_users(count);
    }

    //o último catálogo a ser lido
    public void readReviewFile(String fn, boolean def) throws IOException{

        fn = def ? IGestReviews.REV_PATH : fn;

        int count = -1; //a primeira linha não conta
        BufferedReader br = new BufferedReader(new FileReader(fn));
        String s;

        while((s = br.readLine()) != null){
            try{
                IReview r = Review.parseReview(s);
                if(this.businesses.procura(r.getBusID()) && this.users.procura(r.getUserID()))
                    this.reviews.insere(r);
                else
                    count++;
            }
            catch(BadReview e){
                count++;
            }
        }
        br.close();

        this.stats.set_fn_reviews(fn);
        this.stats.set_bad_reviews(count);
        this.completeStats();
    }

    private void completeStats(){
        this.stats.set_reviewed(this.reviews.businesses_reviewed());
        this.stats.set_impactless(this.reviews.impactless());
        this.stats.set_active_users(this.reviews.reviewers());
    }

    public String queryE1(){
        return this.stats.toString();
    }

    public Par<Set<String>, Integer> query1(){
        Set<String> s = new HashSet<>();
        
        try{
            while(true)
                s.add(this.businesses.proximo().getBusID());
        }
        catch (EndOfCatalogException e){}

        try{
            while(true)
                s.remove(this.reviews.proximo().getBusID());
        }
        catch (EndOfCatalogException e){}

        Set<String> ret = new TreeSet<>();
        for(String str : s)
            ret.add(String.format("| %s |", str));

        return new Par<>(ret, ret.size());
    }

    public String query2(String ano, String mes){
        
        List<IReview> rs = new ArrayList<>();
        IReview r;
        String[] ss;

        try{
            while(true){
                r = this.reviews.proximo();
                ss = r.getDate().split("-");
                if(ss[0].equals(ano) && ss[1].equals(mes))
                   rs.add(r);
            }
        }
        catch (EndOfCatalogException e){}

        long users = rs.stream().map(x -> x.getUserID()).distinct().count();

        return String.format("| %-11.11d | %-9.9d |", rs.size(), users); 
    }


    public List<String> query3(String id){

        List<IReview> revs = new ArrayList<>();
        IReview r;

        try{
            while(true){
                r = this.reviews.proximo();
                if(r.getUserID().equals(id))
                    revs.add(r);
            }
        }
        catch (EndOfCatalogException e){}
        
        int num_revs, distinct;
        float avg;

        List<String> ret = new ArrayList<>();
        Map<String, List<IReview>> perMonth = revs.stream().collect(
                                       Collectors.groupingBy(x -> x.getDate().split("-")[1]));


        for(Map.Entry<String, List<IReview>> e : perMonth.entrySet()){
            num_revs = e.getValue().size();
            distinct = (int) e.getValue().stream().map(IReview::getBusID).distinct().count();
            avg = (float) e.getValue().stream().mapToDouble(IReview::getStars).average().getAsDouble();

            ret.add(String.format("| %-5.5s | %-11.11d | %-8.8d | %-13.13f |", 
                e.getKey(), num_revs, distinct, avg));
        }
        return ret;
    }

    public List<String> query4(String id){

        List<IReview> revs = new ArrayList<>();
        IReview r;

        try{
            while(true){
                r = this.reviews.proximo();
                if(r.getBusID().equals(id))
                    revs.add(r);
            }
        }
        catch (EndOfCatalogException e){}
        
        int num_revs, distinct;
        float avg;
       
        List<String> ret = new ArrayList<>();

        Map<String, List<IReview>> perMonth = revs.stream().collect(
                                       Collectors.groupingBy(x -> x.getDate().split("-")[1]));


        for(Map.Entry<String, List<IReview>> e : perMonth.entrySet()){
            num_revs = e.getValue().size();
            distinct = (int) e.getValue().stream().map(IReview::getUserID).distinct().count();
            avg = (float) e.getValue().stream().mapToDouble(IReview::getStars).average().getAsDouble();

            ret.add(String.format("| %-38.38s |", e.getKey()));
            ret.add(String.format("| %-11.11d | %-8.8d | %-13.13f |", 
                num_revs, distinct, avg));
        }
        return ret;
    }

    public List<String> query5(String id){
        List<IReview> revs = new ArrayList<>();
        IReview r;

        try{
            while(true){
                r = this.reviews.proximo();
                if(r.getUserID().equals(id))
                    revs.add(r);
            }
        }
        catch (EndOfCatalogException e){}

        Comparator<Par<String, Integer>> maisAvaliados =
            (p1, p2) -> {
                if((p2.getValue1() - p1.getValue1()) == 0)
                    return p1.getValue0().compareTo(p2.getValue0());
                else
                    return p2.getValue1() - p1.getValue1();
            };

        Set<Par<String, Integer>> setPares = new TreeSet<>(maisAvaliados);
        List<String> ret = new ArrayList<>();

        revs.stream().collect(Collectors.
                              groupingBy(x -> this.businesses.getBusiness(x.getBusID()).getName())).
                      entrySet().
                      forEach(e -> setPares.add(Par.de(e.getKey(), e.getValue().size())));
        setPares.forEach(p -> ret.add(String.format("| %-14.14s | %-11.11d |", 
                         p.getValue0(), p.getValue1())));
        
        return ret;
    }

    public List<String> query6(int n){

        List<IReview> revs = new ArrayList<>();

        try{
            while(true)
                revs.add(this.reviews.proximo());
        }
        catch (EndOfCatalogException e){}
        
        Comparator<Triplo<String, Integer, Integer>> maisAvaliados =
            (p1, p2) -> {
                return p2.getValue1() - p1.getValue1();
            };
        
        Map<String, List<IReview>> perYear = revs.stream().collect(
                                       Collectors.groupingBy(x -> x.getDate().split("-")[0]));

        int num_revs, distinct;
        List<String> ret = new ArrayList<>();

        for(Map.Entry<String, List<IReview>> e1 : perYear.entrySet()){

            ret.add(String.format("| %-47.47s |", e1.getKey()));

            Set<Triplo<String, Integer, Integer>> setTriples = new TreeSet<>(maisAvaliados);
            Map<String, List<IReview>> perBus = e1.getValue().stream().collect(
                                                Collectors.groupingBy(x -> x.getBusID()));

            for(Map.Entry<String, List<IReview>> e2 : perBus.entrySet()){
                num_revs = e2.getValue().size();
                distinct = (int) e2.getValue().stream().map(IReview::getUserID).distinct().count();
                setTriples.add(Triplo.de(e2.getKey(), num_revs, distinct));
            }

            setTriples.stream().
                       limit(n).
                       collect(Collectors.toList()).
                       forEach(x -> ret.
                              add(String.format("| %s | %-11.11d | %-8.8d |", 
                                         x.getValue0(), x.getValue1(), x.getValue2())));
        }
        return ret;
    }

    public List<String> query7(){

        List<IReview> revs = new ArrayList<>();

        try{
            while(true)
                revs.add(this.reviews.proximo());
        }
        catch (EndOfCatalogException e){}

        Comparator<Par<String, Integer>> mostRevs =
            (p1, p2) -> {
                return p2.getValue1() - p1.getValue1();
            };
            
        Map<String, List<IReview>> byCity = revs.stream().
        collect(Collectors.groupingBy(x -> this.businesses.getBusiness(x.getBusID()).getCity()));
        
        List<String> ret = new ArrayList<>();

        for(Map.Entry<String, List<IReview>> e : byCity.entrySet()){
            ret.add(String.format("| %-36.36s |", e.getKey()));

            e.getValue().stream().
                    collect(Collectors.groupingBy(x -> x.getBusID())).
                    entrySet().
                    stream().
                    map(x -> Par.de(x.getKey(), x.getValue().size())).
                    sorted(mostRevs).
                    collect(Collectors.toList()).
                    forEach(x -> ret.add(String.format("| %s | %-11.11d |", 
                                         x.getValue0(), x.getValue1())));
        }
        return ret;
    }

    public List<String> query8(int n){
        List<IReview> revs = new ArrayList<>();

        try{
            while(true)
                revs.add(this.reviews.proximo());
        }
        catch (EndOfCatalogException e){}

        Comparator<Par<String, Integer>> mostRevs =
            (p1, p2) -> {
                return p2.getValue1() - p1.getValue1();
            };

        List<String> ret = new ArrayList<>(); 
        revs.stream().collect(Collectors.groupingBy(x -> x.getUserID())).
             entrySet().
             stream().
             map(x -> Par.de(x.getKey(), (int) x.getValue().stream().
                                                 map(IReview::getBusID).distinct().count())
                ).
             sorted(mostRevs).
             limit(n).
             collect(Collectors.toList()).
             forEach(x -> ret.add(String.format("| %s | %-11.11d |", 
                              x.getValue0(), x.getValue1())));

        return ret;
    }
    
    public List<String> query9(String id, int n){
        List<IReview> revs = new ArrayList<>();
        IReview r;

        try{
            while(true){
                r = this.reviews.proximo();
                if(r.getBusID().equals(id))
                    revs.add(r);
            }
        }
        catch (EndOfCatalogException e){}

        Comparator<Triplo<String, Integer, Float>> maisAval =
            (p1, p2) -> {
                return p2.getValue1() - p1.getValue1();
            };

        List<String> ret = new ArrayList<>();
            revs.stream().
                collect(Collectors.groupingBy(x -> x.getUserID())).
                entrySet().
                stream().
                map(x -> Triplo.de(x.getKey(), x.getValue().size(), (float)
                    x.getValue().stream().mapToDouble(IReview::getStars).average().getAsDouble())).
                sorted(maisAval).
                limit(n).
                collect(Collectors.toList()).
                forEach(x -> ret.add(String.format("| %s | %-11.11d | %-13.13f |", 
                              x.getValue0(), x.getValue1(), x.getValue2())));
        return ret;
    }

    public List<String> query10(){
        List<IReview> revs = new ArrayList<>();

        try{
            while(true)
                revs.add(this.reviews.proximo());
        }
        catch (EndOfCatalogException e){}
        
        StringBuilder sb = new StringBuilder();
        Function<IReview, String> cityAndState = 
        p1 -> {
            IBusiness r = this.businesses.getBusiness(p1.getBusID());
            sb.append(r.getCity()).append(", ").append(r.getState());
            String s = sb.toString();
            sb.setLength(0);
            return s;
        };

        List<String> ret = new ArrayList<>();
        Map<String, List<IReview>> byCity =
            revs.stream().
                 collect(Collectors.groupingBy(cityAndState));

        for(Map.Entry<String, List<IReview>> e : byCity.entrySet()){
            ret.add(String.format("| %-38.38s |", e.getKey()));
            e.getValue().stream().
              collect(Collectors.groupingBy(x -> x.getBusID())).
              entrySet().
              stream().
              map(x -> Par.de(x.getKey(), (float) x.getValue().stream().
                                                    mapToDouble(IReview::getStars).
                                                    average().getAsDouble())).
              collect(Collectors.toList()).
              forEach(x -> ret.add(String.format("| %s | %-13.13f |", 
                              x.getValue0(), x.getValue1())));
        }
        return ret;
    }
}