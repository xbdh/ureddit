import {auth} from "@clerk/nextjs";
import axios from "axios";


export async function POST(req: Request) {
    const {userId}=auth()
    if(!userId){
        return new Response('Please login', { status: 403 })
    }
    try {

        const body = await req.json()

        const { commentId,voteType } = body

        const createVotePlayload= {
            commentId:commentId,
            userId:userId,
            voteType:voteType
        }
    

        const  {data:newCommentVoteResp}=await axios.post(`${process.env.BACKEND_URL}/createCommentVote`,createVotePlayload)

        const {success}=newCommentVoteResp.data

        console.log("newCommentVotestatus: ",success)
        return new Response('OK', { status: 200 })
    } catch (error) {
        // if (error instanceof z.ZodError) {
        //     return new Response(error.message, { status: 400 })
        // }

        return new Response(
            'Could not post to subreddit at this time. Please try later',
            { status: 500 }
        )
    }
}
