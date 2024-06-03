import {auth} from "@clerk/nextjs";
import {NextRequest, NextResponse} from "next/server";
import axios from "axios";

export async function POST(req:NextRequest){
    const {userId}=auth()
    //if (!userId) return new NextResponse("Unauthorized", {status: 401})
    try {
        const body= await  req.json()
        const name=body.name

        const createSubredditPayload = {
            name: name,
            creatorId: userId,
        };

       // const createSubredditFullUrl=`${process.env.BACKEND_URL}/createSubreddit`;

        const  {data:subredditResp}=await axios.post(`${process.env.BACKEND_URL}/createSubreddit`,createSubredditPayload)
        console.log("createSubredditResp: ",subredditResp)

        // const subredditId=subredditResp as string
        //
        // const insertSubscriptionPayload = {
        //     userId: userId,
        //     subredditId: subredditId,
        // };
        //
        // const  {data:subscriptionresp}= await axios.post("http://127.0.0.1:9999/insertSubscription",insertSubscriptionPayload)
        // console.log("subscriptionresp:",subscriptionresp)
    }catch (e){
        console.log("create subreddit error")
        return new NextResponse("create subreddit error", {status: 500})
    }

    return new NextResponse('', {status: 200})

}
