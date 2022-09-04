import { ResultsStateType } from "../App";
type ResultsPropsType = {
    results:ResultsStateType;
}

// type ResultsPropsType = {
//     results:{
//         country: string;
//         cityName: string;
//         temperature: string;
//         conditionText:string;
//         icon:string;
//     }
// }

// const Results = ({results} :ResultsPropsType) => {  // props省略形
    //const {cityName,country,temperature,conditionText,icon} = results;
const Results = (props :ResultsPropsType) => {
    const {cityName,country,temperature,conditionText,icon} = props.results;
    return (
        <>
            {cityName && <div>{cityName}</div>}
            {temperature && <div><span>{temperature}</span></div>}
            {conditionText && 
                <div className="results-condition">
                    <img src={icon} alt="icon" />
                    <span>{conditionText}</span>
                </div>
            }
        </>
    );
};

export default Results;