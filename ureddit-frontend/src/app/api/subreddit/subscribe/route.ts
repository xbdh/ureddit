import {auth} from "@clerk/nextjs";
import axios from "axios";


export async function POST(req: Request) {
    const {userId}=auth()
    try {
        const body = await req.json()
        const { subredditId } = body


        const createSubscriptionPayload={
            userId:userId,
            subredditId:subredditId
        }
        const {data}=await axios.post(`${process.env.BACKEND_URL}/createSubscription`,createSubscriptionPayload);


        return new Response(subredditId)
    } catch (error) {

        return new Response(
            'Could not subscribe to subreddit at this time. Please try later',
            { status: 500 }
        )
    }
}


function getSubredditNameFromUrl(url:string){
    const parts=url.split("/")
    const subredditName=parts[parts.length-1]
    return subredditName
}