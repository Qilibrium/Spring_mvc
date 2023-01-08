package com.example.demoapp.controller;
import com.example.demoapp.note.Note;
import com.example.demoapp.note.NoteListService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/note")
@RestController
public class NoteController {

    NoteListService service = new NoteListService();


    @GetMapping("/test")
    public ModelAndView helloWorld(){
        ModelAndView result = new ModelAndView("test");
        result.addObject("test");
        return result;
    }
    @GetMapping("/list")
    public ModelAndView allNotes(){
        ModelAndView result = new ModelAndView("notes");
        result.addObject("noteList", service.listAll());
        return result;
    }
    @PostMapping("/delete")
    public RedirectView deleteNoteById(@RequestParam long id){
        service.deleteById(id);
        return new RedirectView("/note/list");
    }
    @GetMapping("/edit")
    public ModelAndView editNote(@RequestParam Long id){
        ModelAndView result = new ModelAndView("edit");
        result.addObject("note", service.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public RedirectView updateNote(@RequestParam long id, @RequestParam String title, @RequestParam String content){
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setContent(content);
        service.update(note);
        return new RedirectView("/note/list");
    }
    @GetMapping("/add")
    public ModelAndView addNote(){
        ModelAndView result = new ModelAndView("edit");
        result.addObject("note", null);
        return result;
    }
    @PostMapping("/add")
    public RedirectView addNote(@RequestParam String title, @RequestParam String content){
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        service.add(note);
        return new RedirectView("/note/list");
    }


}
