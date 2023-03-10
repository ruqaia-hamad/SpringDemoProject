package com.TechM.springDemoProject.Services;

import com.TechM.springDemoProject.Models.Customer;
import com.TechM.springDemoProject.Models.Invoice;
import com.TechM.springDemoProject.Models.Item;
import com.TechM.springDemoProject.Models.Market;
import com.TechM.springDemoProject.Repositories.CustomerRepository;
import com.TechM.springDemoProject.Repositories.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarketService {
    @Autowired
    MarketRepository marketRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Market> getAllMarkets() {
        return marketRepository.getAllMarkets();
    }


    public void addMarket() {
        Market market = new Market();
        market.setName("LULO HYPERMARKET");
        marketRepository.save(market);

    }

    public Market getMarketById(Integer id) {
        Market market = marketRepository.getMarketById(id);
        return market;
    }

    public Market getMarketByName(String name) {
        Market market = marketRepository.getMarketByName(name);
        return market;
    }

    public List<Market> getAllActiveMarkets() {
        List<Market> markets = marketRepository.findAllActive();
        return markets;
    }

    public List<Market> getAllInActiveMarkets() {
        List<Market> markets = marketRepository.findAllInActive();
        return markets;
    }

    public Market findTopByOrderById() {
        Market market = marketRepository.findTopByOrderById();
        return market;

    }


    public void deleteByIdIsActive(Integer id) {
        marketRepository.deleteByIdIsActive(id);

    }


    public void deleteAll() {
        marketRepository.deleteAll();
    }

    public void setCreatedDateByUserInput(String stringDate, Integer id) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(stringDate);
        Market market = marketRepository.getMarketById(id);
        market.setCreatedDate(convetedDate);
        marketRepository.save(market);

    }

    public void createNewMarket(String createdDate, String marketName, boolean isValid) throws ParseException {
        Market market = new Market();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date convetedDate = formatter.parse(createdDate);
        market.setCreatedDate(convetedDate);
        market.setIsActive(isValid);
        market.setName(marketName);

    }


    public void deleteMarketByID(Integer id) {
        Market market = marketRepository.getMarketById(id);
        market.setIsActive(false);
        marketRepository.save(market);
    }

    public void deleteMarketByName(String name) {
        Market market = marketRepository.getMarketByName(name);
        market.setIsActive(false);
        marketRepository.save(market);
    }

    public void deleteAllItems() {
        Iterable<Market> markets = marketRepository.findAll();
        for (Market market : markets) {
            market.setIsActive(false);

        }
        marketRepository.saveAll(markets);
    }


    public void deleteByCreatedDate(Date createdDate) {
        Iterable<Market> markets = marketRepository.findByCreatedDate(createdDate);
        for (Market market : markets) {
            market.setIsActive(false);

        }
        marketRepository.saveAll(markets);

    }

    public void deleteByUpdatedDate(Date updatedDate) {
        Iterable<Market> markets = marketRepository.findByUpdatedDate(updatedDate);
        for (Market market : markets) {
            market.setIsActive(false);

        }
        marketRepository.saveAll(markets);

    }

    public void deleteByCreatedAfterDate(Date date) {
        Iterable<Market> markets = marketRepository.findByCreatedDateAfter(date);
        for (Market market : markets) {
            market.setIsActive(false);

        }
        marketRepository.saveAll(markets);


    }

    public List<Market> getMarketsByNumberOfCustomers() {
        List<Object[]> result = marketRepository.findMarketWithCustomerNumber();
        List<Market> markets = new ArrayList<>();

        for (Object[] obj : result) {
            Market market = new Market();
            market.setId((Integer) obj[0]);
            market.setName((String) obj[1]);
            markets.add(market);
        }

        return markets;
    }


    public Market getMarketCreatedDate(Date createdDate) {
        Market market = marketRepository.getMarketByCreatedDate(createdDate);
        return market;
    }

    public List<Market> getMarketsByNumberOfCustomers(Integer numberOfCustomers ) {
        List<Integer> typesOfMarketIdsInCustomer = customerRepository.getDistinctMarketIdsFromCustomer();
        //{1,2 }

        List<Integer> MarketIdsThatUserWants = new ArrayList<>();

        for (Integer idOfMarket : typesOfMarketIdsInCustomer) {
            Integer count = customerRepository.getCountOfCustomersByMarketId(idOfMarket);
            if (numberOfCustomers == count) {
                MarketIdsThatUserWants.add(idOfMarket);
            }
        }

        List<Market> marketThatUserWasLookingFor = marketRepository.findAllById(MarketIdsThatUserWants);
        return marketThatUserWasLookingFor;
    }}