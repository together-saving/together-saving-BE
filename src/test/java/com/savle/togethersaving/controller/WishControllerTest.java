package com.savle.togethersaving.controller;

import com.savle.togethersaving.entity.Wish;
import com.savle.togethersaving.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WishControllerTest extends ControllerTestUtil {

    @MockBean
    private WishService wishService;

    @Test
    void addWish() throws Exception {
        createUserAndChallenge();
        //given
        given(wishService.addWish(1L, 1L))
                .willReturn(Wish.builder().wishId(1L).hopingPerson(user).challenge(challenge1).build());
        //when
        ResultActions result = mockMvc.perform(
                post("/api/v1/users/challenges/1/wish")
                        .header("user-id", 1L)
                        .accept(MediaType.APPLICATION_JSON)
        );
        //then
        result.andDo(print())
                .andExpect(status().isOk())
        ;
    }

    @Test
    void deleteWish() throws Exception {
        createUserAndChallenge();
        ResultActions result = mockMvc.perform(
                delete("/api/v1/users/challenges/1/wish")
                        .header("user-id", 1L)
                        .accept(MediaType.APPLICATION_JSON)
        );
        //then
        result.andDo(print())
                .andExpect(status().isOk())
        ;
    }
}