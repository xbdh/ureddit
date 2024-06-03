'use client'

import { usePrevious } from '@mantine/hooks'

import { useMutation } from '@tanstack/react-query'
import axios, { AxiosError } from 'axios'
import { useEffect, useState } from 'react'
// import {auth} from "@clerk/nextjs";
import { Button } from '../ui/Button'
import { ArrowBigDown, ArrowBigUp } from 'lucide-react'
import { cn } from '@/lib/utils'


interface PostVoteClientProps {
  postId: string
  initialVotesAmt: number
  initialVote? :string  //要加问号，否则会报错
}

const PostVoteClient = ({
  postId,
  initialVotesAmt,
  initialVote,
}: PostVoteClientProps) => {
//    const {userId}=auth();
 
    const [votesAmt, setVotesAmt] = useState<number>(initialVotesAmt)
    const [currentVote, setCurrentVote] = useState(initialVote)
    const prevVote = usePrevious(currentVote)

  // ensure sync with server
  useEffect(() => {
    setCurrentVote(initialVote)
  }, [initialVote])

  const { mutate: vote } = useMutation({
    mutationFn: async (voteType: string) => {
      const payload= {
        voteType:voteType,
        postId: postId,
        //userId:userId
      }

      await axios.post('/api/subreddit/post/vote', payload)
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
    <div className='flex flex-col gap-4 sm:gap-0 pr-6 sm:w-20 pb-4 sm:pb-0'>
      {/* upvote */}
      <Button
        onClick={() => vote("+")}
        size='sm'
        variant='ghost'
        aria-label='upvote'>
        <ArrowBigUp
          className={cn('h-5 w-5 text-zinc-700', {
            'text-emerald-500 fill-emerald-500': currentVote === "+",
          })}
        />
      </Button>

      {/* score */}
      <p className='text-center py-2 font-medium text-sm text-zinc-900'>
        {votesAmt}
      </p>

      {/* downvote */}
      <Button
        onClick={() => vote("-")}
        size='sm'
        className={cn({
          'text-emerald-500': currentVote === "-",
        })}
        variant='ghost'
        aria-label='downvote'>
        <ArrowBigDown
          className={cn('h-5 w-5 text-zinc-700', {
            'text-red-500 fill-red-500': currentVote === "-",
          })}
        />
      </Button>
    </div>
  )
}

export default PostVoteClient
