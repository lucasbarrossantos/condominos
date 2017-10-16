package com.condominos.controller;

import com.condominos.controller.page.PageWrapper;
import com.condominos.model.Categoria;
import com.condominos.model.Pauta;
import com.condominos.repository.Categorias;
import com.condominos.repository.Pautas;
import com.condominos.repository.Usuarios;
import com.condominos.security.UsuarioSistema;
import com.condominos.service.PautasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/pautas")
public class PautaController {

    private static final String VIEW = "pauta/cadastrar-pauta";
    private static final String SEARCH = "pauta/pesquisar-pautas";
    private static final String DETAIL = "pauta/pautas-detalhe";

    @Autowired
    private PautasService pautasService;

    @Autowired
    private Pautas pautas;

    @Autowired
    private Categorias categorias;

    @Autowired
    private Usuarios usuarios;

    @GetMapping("/nova")
    public ModelAndView nova(Pauta pauta) {
        return new ModelAndView(VIEW);
    }

    @PostMapping("/nova")
    public ModelAndView salvar(@Valid Pauta pauta, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return nova(pauta);
        }

        pautasService.salvar(pauta);
        attributes.addFlashAttribute("mensagem", "Pauta: " + pauta.getId() + " salva com sucesso!");
        return new ModelAndView("redirect:/pautas/nova");
    }

    @GetMapping
    public ModelAndView pesquisar(Pauta pauta, Pageable pageable, HttpServletRequest httpServletRequest) {
        ModelAndView view = new ModelAndView(SEARCH);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descricao", where -> where.contains().ignoreCase());

        Page<Pauta> page = pautas.findAll(Example.of(pauta, matcher), pageable);
        PageWrapper<Pauta> paginaWrapper = new PageWrapper<>(page, httpServletRequest);

        view.addObject("pagina", paginaWrapper);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Pauta pauta) {
        ModelAndView view = nova(pauta);
        view.addObject(pauta);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detalheDaPauta(@PathVariable("id") Pauta pauta, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
        ModelAndView view = new ModelAndView(DETAIL);

        view.addObject("votou", isVotadoPor(usuarioSistema, pauta.getId()));
        view.addObject(pauta);
        view.addObject(new Categoria());
        view.addObject("categorias", categorias.findAllByPautaId(pauta.getId()));
        view.addObject("usuarioSistema", usuarioSistema.getUsuario().getId());
        return view;
    }

    private boolean isVotadoPor(@AuthenticationPrincipal UsuarioSistema usuarioSistema, Long pauta_id) {
        return usuarios.categoriaVotadaPor(usuarioSistema.getUsuario().getId(), pauta_id) != null;
    }

    @PostMapping("/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        pautas.delete(id);
        return new ModelAndView("redirect:/pautas");
    }

}
