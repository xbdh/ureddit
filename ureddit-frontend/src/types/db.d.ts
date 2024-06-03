export type ExtendedPost ={
    id :string,
    subredditId: string,
    title: string,
    content: string,
    authorId: string,
    authorName: string,
    subredditName: string,
    currentVote:string,
    votesAmt: number,
    commentsAmt: number,
    createTime :string,
}

export type Comment= {
    id:string,
    postId:string,
    userId:string,
    content: string,
    replyToId:  string,
    level: number,

    authorName: string,
    image: string,
    replyToName: string,

    currentCommentVote: string,
    commentVotesAmt: number,
    createTime: string,
    
    replies: Comment[],

}