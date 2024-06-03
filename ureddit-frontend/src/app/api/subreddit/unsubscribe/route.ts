import {auth} from "@clerk/nextjs";
import axios from "axios";


export async function POST(req: Request) {

    const {userId}=auth()
    try {
        const body = await req.json()
        const { subredditId } = body

        const deleteSubscriptionPayload={
            userId:userId,
            subredditId:subredditId,
        }
        const {data:isDeleted}=await axios.post(`${process.env.BACKEND_URL}/deleteSubscription`,deleteSubscriptionPayload);


        return new Response(subredditId)
    } catch (error) {

        return new Response(
            'Could not unsubscribe from subreddit at this time. Please try later',
            { status: 500 }
        )
    }
}
