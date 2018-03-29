package com.work.backendlibrary.controller;

import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.repository.UserRepository;
import com.work.backendlibrary.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public VendedorController(UserRepository userRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    @Qualifier("vendedorServiceImpl")
    private VendedorService vendedorService;

    //----Crear un vendedor -----
    @PostMapping(value = "/nuevo")
    public ResponseEntity<Vendedor> crearVendedor(@RequestBody Vendedor vendedor) {
        Vendedor resultVendedor = vendedorService.addVendedor(vendedor);
        if (resultVendedor != null)
            return new ResponseEntity<Vendedor>(vendedor, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Vendedor>> listAll() {
        List<Vendedor> vendedores = vendedorService.listAll();
        if (vendedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vendedores, HttpStatus.OK);
    }

    @GetMapping(value = "/search/clave={clave}")
    public ResponseEntity<Boolean> buscarXclave(@PathVariable String clave) {
        Boolean existeVendedor = vendedorService.findByClave(clave);
        return new ResponseEntity<>(existeVendedor, HttpStatus.OK);
    }

    @GetMapping(value = "/search/rfc={rfc}")
    public ResponseEntity<Boolean> buscarXrfc(@PathVariable String rfc) {
        Boolean exist = vendedorService.findByRfc(rfc);
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }

    @GetMapping(value = "/search/username={username}")
    public ResponseEntity<Boolean> buscarXusername(@PathVariable String username) {
        Boolean exist;
        exist = userRepository.findByUsername(username) != null;
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }

    @GetMapping(value = "/search/email={email}")
    public ResponseEntity<Boolean> buscarXemail(@PathVariable String email) {
        Boolean exist = vendedorService.findByEmail(email);
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }
}
