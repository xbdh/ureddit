'use client'
import React from 'react';
import {Input} from "@/components/ui/Input";
import {useMutation} from "@tanstack/react-query";
import axios from "axios";
import {Button} from "@/components/ui/Button";
import toast, {Toaster} from "react-hot-toast";
import {useRouter} from "next/navigation";


const Page = () => {
    const  router=useRouter();
     const[input,setInput]=React.useState('')
     const{mutate:createCommity,isLoading}= useMutation({
         mutationFn : async () => {
            const payload = {
                name: input,
            };
             const {data}=await axios.post('/api/subreddit', payload);
             // console.log(data)   插入成功后实际上什么都没有返回，所以这里的data是undefined
             return data as string


         },
         onError: (error) => {
                console.log(error);
                error_notify();
         },
         onSuccess: (data) => {
                console.log(data);
                console.log('create new subreddit success');
                success_notify();
                router.push(`/r/${input}`);
            },

       })
    const success_notify = () => toast.success('Success!');
    const error_notify = () => toast.error('Error!');
    const loading_notify = () => toast.loading('Loading...');
    return (
        <div className='container flex items-center h-full max-w-3xl mx-auto'>
           <Toaster/>
            <div className='relative bg-white w-full h-fit p-4 rounded-lg space-y-6'>
                <div className='flex justify-between items-center'>
                    <h1 className='text-xl font-semibold'>Create a Community</h1>
                </div>

                <hr className='bg-red-500 h-px' />

                <div>
                    <p className='text-lg font-medium'>Name</p>
                    <p className='text-xs pb-2'>
                        Community names including capitalization cannot be changed.
                    </p>
                    <div className='relative'>
                        <p className='absolute text-sm left-0 w-8 inset-y-0 grid place-items-center text-zinc-400'>
                            r/
                        </p>
                        <Input
                            value={input}
                            onChange={(e) => setInput(e.target.value)}
                            className='pl-6'
                        />
                       {/*<Input/>*/}
                    </div>
                </div>

                <div className='flex justify-end gap-4'>
                    <Button
                        disabled={isLoading}
                        variant='subtle'>

                        {/*//onClick={() => router.back()}>*/}
                        Cancel
                    </Button>
                    <Button
                        isLoading={isLoading}
                        disabled={input.length === 0}
                        onClick={() =>createCommity()}>
                        Create Community
                    </Button>
                </div>


            </div>
        </div>
    );
};

export default Page;