// import { Editor } from '@/components/Editor'
import { Button } from '@/components/ui/Button'
import { notFound } from 'next/navigation'
import axios from "axios";
import { Editor } from '@/components/Editor'
interface pageProps {
    params: {
        slug: string
    }
}

const page = async ({ params }: pageProps) => {
    const findSubredditByNamePayloadOnly={
        name:params.slug,
    }
    const {data :resp} =await axios.post(`${process.env.BACKEND_URL}/findSubredditByNameOnly`, findSubredditByNamePayloadOnly)
    const {id:subredditId,name:subredditName,creatorId,createTime}=resp.data;
    console.log("get subreddit info only ",subredditId,subredditName,creatorId,createTime);
    if (!subredditId) return notFound();

    return (
        <div className='flex flex-col items-start gap-6'>
            {/* heading */}
            <div className='border-b border-gray-200 pb-5'>
                <div className='-ml-2 -mt-2 flex flex-wrap items-baseline'>
                    <h3 className='ml-2 mt-2 text-base font-semibold leading-6 text-gray-900'>
                        Create Post
                    </h3>
                    <p className='ml-2 mt-1 truncate text-sm text-gray-500'>
                        in r/{params.slug}
                    </p>
                </div>
            </div>

            {/* form */}
            <Editor subredditId={subredditId} />

            <div className='w-full flex justify-end'>
                <Button type='submit' className='w-full' form='subreddit-post-form'>
                    Post
                </Button>
            </div>
        </div>
    )
}

export default page
