import {auth} from "@clerk/nextjs";
import axios from "axios";


export async function POST(req: Request) {
    const {userId}=auth()
    try {

        const body = await req.json()

        const { title, content, subredditId } = body

        console.log("title: ",title)
        console.log("content: ",content)
        console.log("subredditId: ",subredditId)
        console.log("userId: ",userId)

        // // verify user is subscribed to passed subreddit id
        // const subscription = await db.subscription.findFirst({
        //     where: {
        //         subredditId,
        //         userId: session.user.id,
        //     },
        // })
        //
        // if (!subscription) {
        //     return new Response('Subscribe to post', { status: 403 })
        // }
        // 把content转化为json 在转化为string

        const createPostPlayload= {
              title:title,
            // change to string

               content:JSON.stringify(content),
                authorId:userId,
                subredditId :subredditId,
        }
        console.log("====================================")
        console.log("createPostPlayload: ",createPostPlayload)
        console.log("++++++++++++++++++++++++++++++++++++")
        const  {data:newPostResp}=await axios.post(`${process.env.BACKEND_URL}/createPost`,createPostPlayload)

        const {id:newPostId}=newPostResp.data
        console.log("newPostId: ",newPostId)
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
