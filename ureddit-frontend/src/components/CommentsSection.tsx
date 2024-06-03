
import React from 'react'
import CreateComment from './CreateComment'
import axios from 'axios'
import { Comment } from '@/types/db.d'
import PostComment from './comments/PostComment'
import {auth} from '@clerk/nextjs'
interface CommentsSectionProps {
    postId: string

}



const CommentsSection = async ({ postId}: CommentsSectionProps) => {
    const {userId}=auth();
    const getCommentsPayload = {
        postId: postId,
        loginUserId:userId
      }
    const { data: commentsResp } = await axios.post(`${process.env.BACKEND_URL}/findCommentsByPostId`, getCommentsPayload)

    const comments:Comment[] = commentsResp.data as Comment[];
    console.log("comments",comments)

    return (
        <div className='flex flex-col gap-y-4 mt-4'>
          <hr className='w-full h-px my-6' />
    
          <CreateComment postId={postId} />
    
          <div className='flex flex-col gap-y-6 mt-4'>
            {comments?.map((topLevelComment) => {
                const topLevelCommentVotesAmt = topLevelComment.commentVotesAmt
                const topLevelCommentVote = topLevelComment.currentCommentVote

                const replies = topLevelComment.replies
                const topLevelCommentAuthorName = topLevelComment.authorName
                const topLevelCommentReplyToName = topLevelComment.replyToName
                const topLevelCommentReplyToId = topLevelComment.replyToId
                const topLevelCommentContent= topLevelComment.content 
                const topLevelCommentCreateTime = topLevelComment.createTime 
    
                return (
                  <div key={topLevelComment.id} className='flex flex-col'>
                    <div className='mb-2'>
                      <PostComment
                        key={topLevelComment.id}
                        id={topLevelComment.id}
                        comment={topLevelCommentContent}
                        currentVote={topLevelCommentVote}
                        votesAmt={topLevelCommentVotesAmt}
                        postId={postId}
                        authorName={topLevelCommentAuthorName}
                        createdAt={topLevelCommentCreateTime}
                        replyToId={topLevelCommentReplyToId}
                        replies={replies}
                        image={topLevelComment.image}
                      />
                    </div>
    
                  </div>
                )
              })}
          </div>
        </div>
      )
}

export default CommentsSection