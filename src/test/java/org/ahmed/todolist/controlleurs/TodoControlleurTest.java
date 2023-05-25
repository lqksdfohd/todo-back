package org.ahmed.todolist.controlleurs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ahmed.todolist.entite.Todo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControlleurTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestRecupererToutesLesTodos() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titre", Matchers.is("creer le projet todo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titre", Matchers.is("tester le projet todo")));
    }

    @Test
    public void testCreerUneTodo_todoNonValide() throws Exception{
        Todo todoAvecTitreVide = new Todo();
        todoAvecTitreVide.setTitre(" ");
        todoAvecTitreVide.setDescription("titre vide");
        String json = objectMapper.writeValueAsString(todoAvecTitreVide);

        mockMvc.perform(MockMvcRequestBuilders.post("/creer-todo")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.erreurs.length()",Matchers.is(1)));
    }
    @Test
    public void testCreerUneTodo_todoValide() throws Exception{
        Todo todoValide = new Todo();
        todoValide.setTitre("todo valide");
        todoValide.setDescription("une todo valide");
        String json = objectMapper.writeValueAsString(todoValide);

        mockMvc.perform(MockMvcRequestBuilders.post("/creer-todo")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titre", Matchers.is("todo valide")));
    }

    @Test
    public void testCompleterUneTodo_existante() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/completer-todo/{id}", 2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accompli", Matchers.is("TRUE")));
    }

    @Test
    public void testCompleterUneTodo_nonExistante() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/completer-todo/{id}", 3))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
