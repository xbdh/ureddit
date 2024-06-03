import { notFound } from 'next/navigation'
import MiniCreatePost    from "@/components/MiniCreatePost";
import {INFINITE_SCROLL_PAGINATION_RESULTS} from '@/config'
import PostFeed from "@/components/PostFeed";
import axios from 'axios';
import {auth} from "@clerk/nextjs";

interface PageProps {
    params: {
        slug: string
    }
}


const page = async ({ params }: PageProps) => {
    const { slug } = params
    const {userId}=auth();

    const pagePostsPayload = {
        size: INFINITE_SCROLL_PAGINATION_RESULTS,
        pageNum: 1,
        subredditName:slug,
        loginUserId:userId
    }

    const {data:resp}=await axios.post(`${process.env.BACKEND_URL}/findPagePostsBySubredditName`,pagePostsPayload);
    const posts=resp.data;
    //console.log("get posts by subreddit id ",resp.data);

    return (
        <>
            <h1 className='font-bold text-3xl md:text-4xl h-14'>
                r/{slug}
            </h1>
            <MiniCreatePost />
            <PostFeed initialPosts={posts} subredditName={slug}/>
        </>
    )
}

export default page
