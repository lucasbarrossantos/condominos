package com.condominos.controller;

import com.condominos.model.Categoria;
import com.condominos.repository.Categorias;
import com.condominos.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private static final String EDIT = "categoria/cadastrar-categoria";

    @Autowired
    private CategoriasService categoriasService;

    @Autowired
    private Categorias categorias;

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Categoria categoria) {
        ModelAndView view = new ModelAndView(EDIT);
        view.addObject(categoria);
        return view;
    }

    @PostMapping("/nova")
    public ModelAndView salvar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            ModelAndView view = new ModelAndView(EDIT);
            view.addObject("categoria", categoria);
            return view;
        }

        categoriasService.salvar(categoria);
        attributes.addFlashAttribute("mensagem", "Categoria: " + categoria.getId() + " salva com sucesso!");
        return new ModelAndView("redirect:/categorias/"+categoria.getId());
    }


    @PostMapping(value = "/nova-opcao", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<?> novaOpcaoDeVotacao(@RequestBody @Valid Categoria categoria,
                                         BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
        }

        categoria = categoriasService.salvar(categoria);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Categoria categoria = categorias.findOne(id);
        categorias.delete(id);
        return new ModelAndView("redirect:/pautas/detail/"+categoria.getPauta().getId());
    }

}
