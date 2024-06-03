'use client'
import { Button } from '@/components/ui/Button'
import { cn } from '@/lib/utils'

import { usePrevious } from '@mantine/hooks'
import { useMutation } from '@tanstack/react-query'
import axios, { AxiosError } from 'axios'
import { ArrowBigDown, ArrowBigUp } from 'lucide-react'
import { FC, useState } from 'react'

interface CommentVotesProps {
  commentId: string
  votesAmt: number
  currentVote?: string
}


const CommentVotes: FC<CommentVotesProps> = ({
  commentId,
  votesAmt: _votesAmt,
  currentVote: _currentVote,
}) => {
  
  const [votesAmt, setVotesAmt] = useState<number>(_votesAmt)
  const [currentVote, setCurrentVote] = useState(
    _currentVote
  )
  const prevVote = usePrevious(currentVote)

  const { mutate: vote } = useMutation({
    mutationFn: async (commentVotetype: string) => {
      const payload = {
        voteType: commentVotetype ,
        commentId: commentId,
      }

      await axios.post('/api/subreddit/post/comment/vote', payload)
    },
    onError: (err, voteType) => {
      if (voteType === "-"){
        setVotesAmt((prev) => prev - 1)
      } else if(voteType === "+"){
        setVotesAmt((prev) => prev + 1)
    }


      // reset current vote
       setCurrentVote(prevVote)

      if (err instanceof AxiosError) {
        if (err.response?.status === 401) {
            return "You must be logged in to vote."
        }
      }

        return "Something went wrong."
    },
    onMutate: (voteType: string) => {
            if (currentVote==="*"){
                setCurrentVote(voteType)
                if (voteType === "+"){
                    setVotesAmt((prev)=> prev + 1)
                } else if (voteType === "-"){ 
                    setVotesAmt((prev)=> prev - 1  )
                }
            }else{

            
        
            if (currentVote === voteType) {
                // User is voting the same way again, so remove their vote
                    setCurrentVote(undefined)
                    if (voteType === "+"){
                        setVotesAmt((prev) => prev - 1)
                    } else if (voteType === "-"){ 
                        setVotesAmt((prev) => prev + 1)
                    }
                } else {
                // User is voting in the opposite direction, so subtract 2
                     setCurrentVote(voteType)
                     if (voteType === "+") {
                        setVotesAmt((prev) => prev + (currentVote ? 2 : 1))
                    }else if (voteType === "-"){
                        setVotesAmt((prev) => prev - (currentVote ? 2 : 1))
                    }
                  
              }
            }
        
       
    },
  })
  return (
    <div className='flex gap-1'>
      {/* upvote */}
      <Button
        onClick={() => vote('+')}
        size='xs'
        variant='ghost'
        aria-label='upvote'>
        <ArrowBigUp
          className={cn('h-5 w-5 text-zinc-700', {
            'text-emerald-500 fill-emerald-500': currentVote === '+',
          })}
        />
      </Button>

      {/* score */}
      <p className='text-center py-2 px-1 font-medium text-xs text-zinc-900'>
        {votesAmt}
      </p>

      {/* downvote */}
      <Button
        onClick={() => vote('-')}
        size='xs'
        className={cn({
          'text-emerald-500': currentVote === '-',
        })}
        variant='ghost'
        aria-label='downvote'>
        <ArrowBigDown
          className={cn('h-5 w-5 text-zinc-700', {
            'text-red-500 fill-red-500': currentVote ==="-",
          })}
        />
      </Button>
    </div>
  )
}

export default CommentVotes
