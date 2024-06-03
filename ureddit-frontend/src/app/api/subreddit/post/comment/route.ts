import {auth} from "@clerk/nextjs";
import axios from "axios";

export async function POST(req: Request) {
    const {userId}=auth()
  try {
    const body = await req.json()

    const { postId, text, replyToId } = body

    // String postId;
    // String userId;
    // String content;
    // String replyToId;

    const createCommentPlayload= {
        postId:postId,
        content:text,
        replyToId:replyToId,
        userId:userId,
    }

    const {data:newCommentResp}=await axios.post(`${process.env.BACKEND_URL}/insertComment`,createCommentPlayload)
    const {id:newCommentId}=newCommentResp.data
    console.log("newCommentId: ",newCommentId)
    return new Response('OK')
  } catch (error) {
    
    //   return new Response(error.message, { status: 400 })
    

    return new Response(
      'Could not post to subreddit at this time. Please try later',
      { status: 500 }
    )
  }
}
