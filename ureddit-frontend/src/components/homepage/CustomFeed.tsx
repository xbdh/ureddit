import { INFINITE_SCROLL_PAGINATION_RESULTS } from '@/config'
import { auth } from '@clerk/nextjs'
import PostFeed from '@/components/PostFeed'
import { notFound } from 'next/navigation'
import axios from 'axios'

const CustomFeed = async () => {
    // const session = useSession()
    const {userId} = auth();


    const pagePostsPayload = {
        size: INFINITE_SCROLL_PAGINATION_RESULTS,
        pageNum: 1,
        loginUserId:userId
    }

    const {data:resp}=await axios.post(`${process.env.BACKEND_URL}/findPagePostsBySubredditName`,pagePostsPayload);
    const posts=resp.data;

   return <PostFeed initialPosts={posts} />
}

export default CustomFeed
