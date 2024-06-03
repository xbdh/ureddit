
import PostFeed from '../PostFeed'
import { INFINITE_SCROLL_PAGINATION_RESULTS } from '@/config'
import axios from 'axios'
const GeneralFeed = async () => {
    const pagePostsPayload = {
        size: INFINITE_SCROLL_PAGINATION_RESULTS,
        pageNum: 1,

    }

    const {data:resp}=await axios.post(`${process.env.BACKEND_URL}/findPagePostsBySubredditName`,pagePostsPayload);
    const posts=resp.data;

  return <PostFeed initialPosts={posts} />
}

export default GeneralFeed
