
import axios from 'axios'
import {auth} from "@clerk/nextjs";
export async function POST(req: Request){
    const {userId}=auth()
    try {
        const body = await req.json()
        console.log("body",body)
        const { subredditName ,pageNum,size} = body

        const pagePayload ={
            subredditName:subredditName,
            pageNum:pageNum,
            size:size,
            loginUserId:userId
        }

        const {data:resp}=await axios.post(`${process.env.BACKEND_URL}/findPagePostsBySubredditName`,pagePayload);
        const posts=resp.data;
    

    return new Response(JSON.stringify(posts))
  } catch (error) {
    return new Response('Could not fetch posts', { status: 500 })
  }
}
