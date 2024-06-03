'use client'


import { useMutation } from '@tanstack/react-query'
import axios from 'axios'
import { MessageSquare } from 'lucide-react'
import { useRouter } from 'next/navigation'
import { FC, useRef, useState } from 'react'

import { Button } from '../ui/Button'
import { Label } from '../ui/Label'
import { Textarea } from '../ui/Textarea'

import { Comment } from '@/types/db.d'
import { format } from 'path'
import { formatTimeToNow } from '@/lib/utils'
import { Avatar,AvatarFallback ,AvatarImage} from '../ui/avatar'
import { set } from 'date-fns'
import CommentVotes from '../CommentVotes';




interface PostCommentProps {
  id:string
  comment: string,
  votesAmt: number,
  currentVote:string,
  postId: string,
  authorName:string,
  image:string,
  createdAt:string,
  replyToId: string
  replies:Comment[]
}

type CommentRequest = {
    postId: string
    text: string
    replyToId?: string
}

const PostComment: FC<PostCommentProps> = ({
  id,
  comment,
  votesAmt,
  currentVote,
  postId,
  authorName,
  image,
  createdAt,
  replyToId,
  replies
}) => {
  
  const [isReplying, setIsReplying] = useState<boolean>(false)
  const commentRef = useRef<HTMLDivElement>(null)
  // const [input, setInput] = useState<string>(`@${authorName} `)
  const [input, setInput] = useState<string>( "")
  const router = useRouter()
 

  const { mutate: postComment, isLoading } = useMutation({
    mutationFn: async ({ postId, text, replyToId }: CommentRequest) => {
      const payload: CommentRequest = { postId, text, replyToId }

      const { data } = await axios.post(
        `/api/subreddit/post/comment/`,
        payload
      )
      return data
    },

    onError: () => {
      
    },
    onSuccess: () => {
      router.refresh()
      setIsReplying(false)
      setInput('')
    },
  })

  return (
    <div ref={commentRef} className='flex flex-col'>
      <div className='flex items-center'>
      <Avatar className='h-6 w-6'>
          <AvatarImage src={image} />
          <AvatarFallback>UR</AvatarFallback>
        </Avatar>

      
        <div className='ml-2 flex items-center gap-x-2'> 
          <p className='text-sm font-medium text-gray-900'>u/{authorName}</p>
          <p className='max-h-40 truncate text-xs text-zinc-500'>
            {formatTimeToNow(new Date(createdAt))}
          </p>
        </div>
      </div>

      <p className='text-sm text-zinc-900 mt-2 px-0'>{comment}</p>

      <div className='flex gap-2 items-center'>
        <CommentVotes
         key={id}
          commentId={id}
          votesAmt={votesAmt}
          currentVote={currentVote}
        />

        <Button
          onClick={() => {
            // if (!session) return router.push('/sign-in')
            setIsReplying(true)
          }}
          variant='ghost'
          size='xs'>
          <MessageSquare className='h-4 w-4 mr-1.5' />
          Reply
        </Button>
      </div>

      {isReplying ? (
        <div className='grid w-full gap-1.5'>
          <Label htmlFor='comment'>Your comment</Label>
          <div className='mt-2'>
            <Textarea
              onFocus={(e) =>
                e.currentTarget.setSelectionRange(
                  e.currentTarget.value.length,
                  e.currentTarget.value.length
                )
              }
              autoFocus
              id='comment'
              value={input}
              onChange={(e) =>
                 setInput(e.target.value)}
              rows={1}
              placeholder='What are your thoughts?'
            />

            <div className='mt-2 flex justify-end gap-2'>
              <Button
                tabIndex={-1}
                variant='subtle'
                onClick={() => setIsReplying(false)}>
                Cancel
              </Button>
              <Button
                isLoading={isLoading}
                onClick={() => {
                  if (!input) return
                  postComment({
                    postId,
                    text: input,
                    replyToId:id , // default to top-level comment
                  })
            
                }}>
                Post
              </Button>
            </div>
          </div>
        </div>
      ) : null}

        {replies?.length>0 ? (
          // 画一条竖线 与里面的组件分开一段距离
          // 里面的组件是一个PostComment
          
            <div >
            {replies.map((reply) => (
              <div className='border-l-2 pl-2 rounded-lg border-gray-300 ml-4 mt-4'>

               <PostComment 
                id={reply.id}
                key={reply.id}
                comment={reply.content}
                votesAmt={reply.commentVotesAmt}
                currentVote={reply.currentCommentVote}
                postId={reply.postId}
                replyToId={postId}
                authorName={reply.authorName}
                createdAt={reply.createTime}
                replies={reply.replies}
                image={reply.image}
                />
                </div>
              
            ))}
            </div>
        ) : null}

    </div>
  )
}

export default PostComment
