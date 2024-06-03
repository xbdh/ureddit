'use client'

import { Button } from '@/components/ui/Button'

import { useMutation } from '@tanstack/react-query'
import axios, { AxiosError } from 'axios'
import { useRouter } from 'next/navigation'
import { FC, useState } from 'react'
import { Label } from '@/components/ui/Label'
import { Textarea } from '@/components/ui/Textarea'

interface CreateCommentProps {
  postId: string
  replyToId?: string
}

type CommentRequest = {
    postId: string
    text: string
    replyToId?: string
}

const CreateComment: FC<CreateCommentProps> = ({ postId, replyToId }) => {
  const [input, setInput] = useState<string>('')
  const router = useRouter()

  const { mutate: comment, isLoading } = useMutation({
    mutationFn: async ({ postId, text, replyToId }:CommentRequest) => {
      const payload :CommentRequest={ postId, text, replyToId }

      const { data } = await axios.post(
        `/api/subreddit/post/comment/`,
        payload
      )
      return data as string
    },

    onError: (err) => {
      if (err instanceof AxiosError) {
        if (err.response?.status === 401) {
          return "You must be logged in to comment."
        }
      }

      return "Something went wrong. Please try again."
    },
    onSuccess: () => {
      router.refresh()
      setInput('')
    },
  })

  return (
    <div className='grid w-full gap-1.5'>
      <Label htmlFor='comment'>Your comment</Label>
      <div className='mt-2'>
        <Textarea
          id='comment'
          value={input}
          onChange={(e) =>
             setInput(e.target.value)}
          rows={1}
          placeholder='What are your thoughts?'
        />

        <div className='mt-2 flex justify-end'>
          <Button
            isLoading={isLoading}
            disabled={input.length === 0}
            onClick={() => 
            comment({ postId, text: input, replyToId })}>
            Post
          </Button>
        </div>
      </div>
    </div>
  )
}

export default CreateComment
