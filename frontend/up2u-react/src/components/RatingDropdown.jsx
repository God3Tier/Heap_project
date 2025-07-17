

export function RatingDropdown({ onChange }){
    return(
        <>
        <label>Rating:</label>
        <select defaultValue="0" onChange={onChange}>
            <option value="0" disabled>Select Rating</option>
            <option value="1">1 Star</option>
            <option value="2">2 Star</option>
            <option value="3">3 Star</option>
            <option value="4">4 Star</option>
            <option value="5">5 Star</option>
        </select>
        </>
    )
}