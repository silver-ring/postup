/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appzone.postup.backend.endpoints;

import com.appzone.postup.backend.json.PostCommentJson;
import com.appzone.postup.backend.model.CommentEntity;
import com.appzone.postup.backend.model.CommentRepository;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Mohamed Morsy
 */

public class CommentEndpointTest {

    //parameters
    @DataProvider(name = "testPostComment")
    public static Object[][] testPostCommentParmeters() {
        return new Object[][]{{"test", 1, "text", CommentEntity.Status.WAITING_FOR_APPROVE}};
    }

    @Test(dataProvider = "testPostComment")
    public void testPostComment(String inputText, int expectedId, String expectedText, CommentEntity.Status expectedStatus) {

        // mock
        CommentEndpoint commentEndpoint = Mockito.spy(CommentEndpoint.class);
        CommentRepository commentRepository = Mockito.mock(CommentRepository.class);
        
        // init
        Whitebox.setInternalState(commentEndpoint, "commentRepository", commentRepository);
        
        
        // expected
        CommentEntity expectedCommentEntity = new CommentEntity();
        expectedCommentEntity.setId(expectedId);
        expectedCommentEntity.setStatus(expectedStatus);
        expectedCommentEntity.setText(expectedText);

        // input        
        Mockito.doReturn(expectedCommentEntity).when(commentRepository).save(Matchers.any(CommentEntity.class));
        PostCommentJson postCommentJson = new PostCommentJson();
        postCommentJson.setText(inputText);

        // call
        CommentEntity actualCommentEntity = commentEndpoint.postComment(postCommentJson);

        // verify
        Assert.assertEquals(actualCommentEntity.getId(), expectedCommentEntity.getId());
        Assert.assertEquals(actualCommentEntity.getText(), expectedCommentEntity.getText());
        Assert.assertEquals(actualCommentEntity.getStatus(), expectedCommentEntity.getStatus());

    }

    @Test
    public void testFindComments() {

    }

    @Test
    public void testApproveComment() {

    }

    @Test
    public void testRejectComment() {

    }

}
