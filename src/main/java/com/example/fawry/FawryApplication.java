package com.example.fawry;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FawryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FawryApplication.class, args);
    }

    @Bean
    ApplicationRunner init(ServiceRepository serviceRepository, ServiceProviderRepository serviceProviderRepository) {
        return (ApplicationArguments args) ->  {
            Service mobileRecharge = new Service("Mobile Recharge Services");
            Service internetPayment = new Service("Internet Payment Services");
            Service landline = new Service("Landline Services");
            Service donations = new Service("Donations");
            serviceRepository.saveAll(List.of(mobileRecharge, internetPayment, landline, donations));

            serviceProviderRepository.save(new ServiceProvider("Vodafone", mobileRecharge, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("Etisalat", mobileRecharge, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("Orange", mobileRecharge, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("We", mobileRecharge, false, List.of("Mobile Number", "Amount")));

            serviceProviderRepository.save(new ServiceProvider("Vodafone", internetPayment, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("Etisalat", internetPayment, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("Orange", internetPayment, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("We", internetPayment, false, List.of("Mobile Number", "Amount")));

            serviceProviderRepository.save(new ServiceProvider("Monthly Receipt", landline, false, List.of("Mobile Number", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("Quarter Receipt", landline, false, List.of("Mobile Number", "Amount")));

            serviceProviderRepository.save(new ServiceProvider("Cancer Hospital", donations, true, List.of("Name", "Address", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("Schools", donations, true, List.of("Name", "Address", "Amount")));
            serviceProviderRepository.save(new ServiceProvider("NGOs", donations, true, List.of("Name", "Address", "Amount")));
        };
    }

}
