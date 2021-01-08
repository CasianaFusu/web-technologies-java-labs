package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.*;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizatorService implements UserDetailsService {
    private final UtilizatorRepository utilizatorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UtilizatorService(UtilizatorRepository utilizatorRepository){
        this.utilizatorRepository = utilizatorRepository;
    }

    public List<Utilizator> getAll(){
        return utilizatorRepository.findAll();
    }

    public Utilizator findById(int id){
        return utilizatorRepository.findById(id).orElseThrow(()-> new RuntimeException("Utilizatorul nu a fost gasit."));
    }

    public Utilizator create(Utilizator request){
        return utilizatorRepository.create(request);
    }

    public int update(Utilizator request) {
        return utilizatorRepository.update(request);
    }

    public Utilizator findByUsername(String username){
        return utilizatorRepository.findByUsername(username).get();
    }
    //TODO: verificari
    public String delete(int id){
            return utilizatorRepository.delete(id);
    }

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Utilizator> optionalUser = utilizatorRepository.findByUsername(userName);
        if(optionalUser.isPresent()) {
            Utilizator users = optionalUser.get();

            List<String> roleList = new ArrayList<String>();
            roleList.add("USER");
            return User.builder()
                    .username(users.getUsername())
                    //change here to store encoded password in db
                    .password( users.getParola() )
                    .disabled(users.isActivat())
                    .roles(roleList.toArray(new String[0]))
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
