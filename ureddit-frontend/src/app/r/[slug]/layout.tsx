
import type { Metadata } from 'next'
import Link from 'next/link'
import { ReactNode } from 'react'
import {auth} from "@clerk/nextjs";
import axios from "axios";
import {buttonVariants} from "@/components/ui/Button";
import SubscribeLeaveToggle from "@/components/SubscribeLeaveToggle";
import { create } from 'domain';
import { formatTimeToNow } from '@/lib/utils'

export const metadata: Metadata = {
    title: 'Ureddit',
    description: 'A Reddit clone built with Next.js and Java',
}


const Layout = async ({children, params: { slug }}: {
    children: ReactNode
    params: { slug: string }
}) => {
    const {userId}=auth();


    const findSubredditByNamePayload={
        name:slug,
        loginUserId:userId
    }
    const {data:resp}=await axios.post(`${process.env.BACKEND_URL}/findSubredditByName`,findSubredditByNamePayload);
    const {id:subredditId,name:subredditName,creatorId,createTime,subscribed:isSubscribed,count}=resp.data;
    console.log("get subreddit info all ",subredditId,subredditName,creatorId,createTime,isSubscribed,count);;

    return (
        <div className='sm:container max-w-7xl mx-auto h-full pt-12'>
            <div>
                {/*<ToFeedButton />*/}

                <div className='grid grid-cols-1 md:grid-cols-3 gap-y-4 md:gap-x-4 py-6'>
                    <ul className='flex flex-col col-span-2 space-y-6'>
                      
                        {children}
        
                        
                    </ul>

                    {/* info sidebar */}
                    <div className='overflow-hidden h-fit rounded-lg border border-gray-200 order-first md:order-last'>
                        <div className='px-6 py-4'>
                            <p className='font-semibold py-3'>About r/{subredditName}</p>
                        </div>
                        <dl className='divide-y divide-gray-100 px-6 py-4 text-sm leading-6 bg-white'>
                            <div className='flex justify-between gap-x-4 py-3'>
                                <dt className='text-gray-500'>Created</dt>
                                <dd className='text-gray-700'>
                                    {formatTimeToNow(new Date(createTime))}
                                </dd>

                            </div>
                            <div className='flex justify-between gap-x-4 py-3'>
                                <dt className='text-gray-500'>Members</dt>
                                <dd className='flex items-start gap-x-2'>

                                    <div className='text-gray-900'>{count}</div>
                                </dd>
                            </div>
                            {creatorId === userId ? (
                                <div className='flex justify-between gap-x-4 py-3'>
                                    <dt className='text-gray-500'>You created this community</dt>
                                </div>
                            ) : null}

                            {creatorId !== userId ? (
                                <SubscribeLeaveToggle
                                    isSubscribed={isSubscribed}
                                    subredditId={subredditId}
                                    subredditName={subredditName}
                                />
                            ) : null}
                            <Link
                                className={buttonVariants({
                                    variant: 'outline',
                                    className: 'w-full mb-6',
                                })}
                                href={`/r/${slug}/submit`}>
                                Create Post
                            </Link>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Layout
