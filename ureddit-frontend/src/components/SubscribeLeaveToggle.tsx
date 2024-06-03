'use client'
import { Button } from '@/components/ui/Button'
import { useMutation } from '@tanstack/react-query'
import { useRouter } from 'next/navigation'
import { startTransition } from 'react'
import axios from "axios";


interface SubscribeLeaveToggleProps {
    isSubscribed: boolean
    subredditId: string
    subredditName: string
}

const SubscribeLeaveToggle = ({isSubscribed, subredditId, subredditName,}:
                                  SubscribeLeaveToggleProps) => {

    const router = useRouter()

    const { mutate: subscribe, isLoading: isSubLoading } = useMutation({
        mutationFn: async () => {
            const payload = {
                subredditId,
            }

            const { data } = await axios.post('/api/subreddit/subscribe', payload)
            return data as string
        },
        onError: (err) => {

        },
        onSuccess: () => {
            startTransition(() => {
                // Refresh the current route and fetch new data from the server without
                // losing client-side browser or React state.
                router.refresh()
            })

        },
    })

    const { mutate: unsubscribe, isLoading: isUnsubLoading } = useMutation({
        mutationFn: async () => {
            const payload = {
                subredditId,
            }

            const { data } = await axios.post('/api/subreddit/unsubscribe', payload)
            return data as string
        },
        onError: (err) => {

        },
        onSuccess: () => {
            startTransition(() => {
                // Refresh the current route and fetch new data from the server without
                // losing client-side browser or React state.
                router.refresh()
            })

        },
    })

    return isSubscribed ? (
        <Button
            className='w-full mt-1 mb-4'
            isLoading={isUnsubLoading}
            onClick={() => unsubscribe()}>
            Leave community
        </Button>
    ) : (
        <Button
            className='w-full mt-1 mb-4'
            isLoading={isSubLoading}
            onClick={() => subscribe()}>
            Join to post
        </Button>
    )
}

export default SubscribeLeaveToggle
