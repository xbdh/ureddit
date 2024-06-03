import { create } from 'zustand'

interface BearState {
  subredditId:string,
  subredditName:string,
  setSubredditId: (id: string) => void
  setSubredditName: (name: string) => void

}

const useBearStore = create<BearState>()((set) => ({    
  subredditId: '',
  subredditName:'',
  setSubredditId: (id) => set((state) => ({subredditId: id })),  
  setSubredditName: (name) => set((state) => ({subredditName: name })),  
    
}))

export default useBearStore 