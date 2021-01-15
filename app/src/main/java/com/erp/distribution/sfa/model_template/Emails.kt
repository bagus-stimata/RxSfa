package com.erp.distribution.sfa.model_template

import com.erp.distribution.sfa.model_template.Email.EmailBuilder
import java.util.*

/**
 * Julho, 03 2019
 *
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
object Emails {
    fun fakeEmails(): List<Email> {
        return Arrays.asList(
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Veja nossas três dicas principais para aumentar as suas vendas")
                .setPreview("Olá Tiago, Você precisa ver esse site para")
                .setDate("5 jun")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Um amigo quer que você curta uma Página dele")
                .setPreview("Fulano convidou você para curtir a sua Página no Facebook")
                .setDate("30 mai")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Youtube")
                .setSubject("Tiago Aguiar acabou de enviar um video")
                .setPreview("Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION")
                .setDate("30 mai")
                .setStared(true)
                .setUnread(true)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Instagram")
                .setSubject("tiagoaguiar.oficial começou a seguir-te")
                .setPreview("tiagoaguiar.oficial, tens um novo seguidor")
                .setDate("18 mai")
                .setStared(false)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Veja nossas três dicas principais para aumentar as suas vendas")
                .setPreview("Olá Tiago, Você precisa ver esse site para")
                .setDate("5 jun")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Um amigo quer que você curta uma Página dele")
                .setPreview("Fulano convidou você para curtir a sua Página no Facebook")
                .setDate("30 mai")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Youtube")
                .setSubject("Tiago Aguiar acabou de enviar um video")
                .setPreview("Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION")
                .setDate("30 mai")
                .setStared(true)
                .setUnread(true)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Instagram")
                .setSubject("tiagoaguiar.oficial começou a seguir-te")
                .setPreview("tiagoaguiar.oficial, tens um novo seguidor")
                .setDate("18 mai")
                .setStared(false)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Veja nossas três dicas principais para aumentar as suas vendas")
                .setPreview("Olá Tiago, Você precisa ver esse site para")
                .setDate("5 jun")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Um amigo quer que você curta uma Página dele")
                .setPreview("Fulano convidou você para curtir a sua Página no Facebook")
                .setDate("30 mai")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Youtube")
                .setSubject("Tiago Aguiar acabou de enviar um video")
                .setPreview("Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION")
                .setDate("30 mai")
                .setStared(true)
                .setUnread(true)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Instagram")
                .setSubject("tiagoaguiar.oficial começou a seguir-te")
                .setPreview("tiagoaguiar.oficial, tens um novo seguidor")
                .setDate("18 mai")
                .setStared(false)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Veja nossas três dicas principais para aumentar as suas vendas")
                .setPreview("Olá Tiago, Você precisa ver esse site para")
                .setDate("5 jun")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Facebook")
                .setSubject("Um amigo quer que você curta uma Página dele")
                .setPreview("Fulano convidou você para curtir a sua Página no Facebook")
                .setDate("30 mai")
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Youtube")
                .setSubject("Tiago Aguiar acabou de enviar um video")
                .setPreview("Tiago Aguiar enviou ANDROID: GOOGLE MAPS, LOCATION")
                .setDate("30 mai")
                .setStared(true)
                .setUnread(true)
                .build(),
            EmailBuilder.Companion.builder()
                .setUser("Instagram")
                .setSubject("tiagoaguiar.oficial começou a seguir-te")
                .setPreview("tiagoaguiar.oficial, tens um novo seguidor")
                .setDate("18 mai")
                .setStared(false)
                .build()
        )
    }
}