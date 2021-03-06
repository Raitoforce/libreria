package com.work.backendlibrary.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.work.backendlibrary.entity.Vendedor;
import com.work.backendlibrary.model.VendedorModel;
import com.work.backendlibrary.repository.UserRepository;
import com.work.backendlibrary.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
            return new ResponseEntity<Vendedor>(vendedor, HttpStatus.CREATED);
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
    
    @GetMapping("/Vendedores")
    public ResponseEntity<List<VendedorModel>> listAllModel() {
        List<VendedorModel> vendedores = vendedorService.listAllModel();
        if (vendedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vendedores, HttpStatus.OK);
    }
    
    @GetMapping("/pagina")
    public ResponseEntity<List<VendedorModel>> listAllpage(Pageable pageable) {
        List<VendedorModel> vendedores = vendedorService.listPage(pageable);
        if (vendedores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vendedores, HttpStatus.OK);
    }

    // Borrar vendedor
    @DeleteMapping(value ="/{clave}")
    public ResponseEntity<String> eliminarDirector(@PathVariable("clave") String clave){
        vendedorService.removeVendedor(clave);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    // Obtener un vendedor
    @GetMapping("/{clave}")
    public ResponseEntity<Vendedor> consultarVendedor(@PathVariable("clave") String clave){
        Vendedor vendedorm= vendedorService.consultarVendedor(clave);
        return new ResponseEntity<Vendedor>(vendedorm,HttpStatus.OK);

    }

    // Actualizar datos de vendedor
    @PutMapping
    public ResponseEntity<String> actualizarVendedor(@RequestBody Vendedor vendedorm){
        vendedorService.updateVendedor(vendedorm);
        return new ResponseEntity<>(HttpStatus.OK);
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
