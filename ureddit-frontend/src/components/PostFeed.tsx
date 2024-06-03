'use client'

import {INFINITE_SCROLL_PAGINATION_RESULTS} from '@/config'
import { ExtendedPost } from '@/types/db'
import { useIntersection } from '@mantine/hooks'
import { useInfiniteQuery } from '@tanstack/react-query'
import axios from 'axios'
import { Loader2 } from 'lucide-react'
import { FC, useEffect, useRef } from 'react'
import useBearStore from '@/store'
import Post from './Post'
// import Post from './Post'
// import { useSession } from 'next-auth/react'

interface PostFeedProps {
    initialPosts: ExtendedPost[],
    subredditName?:string
}

const PostFeed: FC<PostFeedProps> = ({ initialPosts,subredditName}) => {
  const lastPostRef = useRef<HTMLElement>(null)
  const { ref, entry } = useIntersection({
    root: lastPostRef.current,
    threshold: 1,
  })
 
  const { data, fetchNextPage,hasNextPage, isFetchingNextPage } = useInfiniteQuery(

    ['infinite-query'], // The query key for this query
   
    async ({ pageParam = 1 }) => {
    const pagePostsPayload = {
        size: INFINITE_SCROLL_PAGINATION_RESULTS,
        pageNum: pageParam,
        subredditName:subredditName,
    }
  

    console.log("pagePostsPayload",pagePostsPayload)
   
    const { data } = await axios.post("/api/posts", pagePostsPayload)
    

    // console.log("从api route 获取的data",data)
    return data as ExtendedPost[] //好像data为空时就不获取下一页了
  },
   {
    getNextPageParam: (lastPage, pages) => {
        //  console.log("下last page",lastPage)
        //  console.log("下next page num",pages.length+1) 
        //  console.log("下pages",pages)
        if (lastPage.length != INFINITE_SCROLL_PAGINATION_RESULTS) return null  
        //最后一页不满足INFINITE_SCROLL_PAGINATION_RESULTS，说明没有下一页了
         return pages.length + 1 
      },
    
    //initialPageParam: 1,
    initialData: { pages: [initialPosts], pageParams: [1] },
   })

  useEffect(() => {
    
    if (entry?.isIntersecting&&hasNextPage) {
      fetchNextPage() // Load more posts when the last post comes into view
    }
  }, [entry, fetchNextPage,hasNextPage])
//   console.log("feedposts" ,initialPosts)
  const posts = data?.pages.flatMap((page) => page) ?? initialPosts
//   console.log("最终展示的post" ,posts)

  return (
    <ul className='flex flex-col col-span-2 space-y-6' >
      {posts.map((post, index) => {
       

        if (index === posts.length - 1) {
          // Add a ref to the last post in the list
          return (
            <li key={post.id} ref={ref}>
                <Post post={post} 
                subredditName={subredditName?subredditName:""} 

                />
            </li>
          )
        } else {
          return (
            <li key={post.id} >
             <Post post={post} subredditName={subredditName?subredditName:""} />
            </li>
          )
        }
        })}

      {isFetchingNextPage && (
        <li className='flex justify-center'>
          <Loader2 className='w-6 h-6 text-zinc-500 animate-spin' />
        </li>
      )}
      {
        !hasNextPage&&(
            <li className='flex justify-center'>
            <p className='text-zinc-500'>No more posts</p>
            </li>
        )
      }

    </ul>
  )
}

export default PostFeed
