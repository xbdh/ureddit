
import EditorOutput from '@/components/EditorOutput'
import { buttonVariants } from '@/components/ui/Button'
import {auth} from "@clerk/nextjs";
import { ArrowBigDown, ArrowBigUp, Loader2 } from 'lucide-react'
import { notFound } from 'next/navigation'
import { Suspense } from 'react'
import axios from 'axios';
import PostVoteServer from '@/components/post-vote/PostVoteServer'
import CommentsSection from '@/components/CommentsSection'
import { formatTimeToNow } from '@/lib/utils'
interface SubRedditPostPageProps {
  params: {
    postId: string
  }
}

export const dynamic = 'force-dynamic'
export const fetchCache = 'force-no-store'

const SubRedditPostPage = async ({ params }: SubRedditPostPageProps) => {
    const { postId } = params
    const {userId}=auth();
    const getPostPayload = {
        id: postId,
        loginUserId:userId
    }
    const {data:resp}=await axios.post(`${process.env.BACKEND_URL}/findPostById`,getPostPayload);
    const {id,title,content,authorId,authorName,subredditId,currentVote,votesAmt,createTime}=resp.data;
  
 


  return (
    // 加个浅颜色边框


    <div className='border border-gray-200 rounded-sm'>
      <div className='h-full flex flex-col sm:flex-row items-center sm:items-start justify-between'>
        <Suspense fallback={<PostVoteShell />}>
         
          <PostVoteServer
            postId={id}
          
            initialVotesAmt={votesAmt}
            initialVote={currentVote}
          />
        </Suspense>

        <div className='sm:w-0 w-full flex-1 bg-white p-4 rounded-sm'>
          <p className='max-h-40 mt-1 truncate text-xs text-gray-500'>
            Posted by u/{authorName}
            <span className='px-1 '>•</span>
            {formatTimeToNow(new Date(createTime))}
          </p>
          <h1 className='text-xl font-semibold py-2 leading-6 text-gray-900'>
            {title}
          </h1>

          <EditorOutput content={content} />
          <Suspense
            fallback={
              <Loader2 className='h-5 w-5 animate-spin text-zinc-500' />
            }>
            
            <CommentsSection postId={id} />
          </Suspense>
        </div>
      </div>
    </div>
  )
}

function PostVoteShell() {
  return (
    <div className='flex items-center flex-col pr-6 w-20'>
      {/* upvote */}
      <div className={buttonVariants({ variant: 'ghost' })}>
        <ArrowBigUp className='h-5 w-5 text-zinc-700' />
      </div>

      {/* score */}
      <div className='text-center py-2 font-medium text-sm text-zinc-900'>
        <Loader2 className='h-3 w-3 animate-spin' />
      </div>

      {/* downvote */}
      <div className={buttonVariants({ variant: 'ghost' })}>
        <ArrowBigDown className='h-5 w-5 text-zinc-700' />
      </div>
    </div>
  )
}

export default SubRedditPostPage
