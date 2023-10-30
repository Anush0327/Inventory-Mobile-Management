export default function Provider(props) {
    const getProviderImage = () => {
        switch (props.provider.type) {
            case 'AIRTEL':
                return "https://th.bing.com/th/id/OIP._jJ1arvIvWSWQGbcukTzkwHaHK?pid=ImgDet&rs=1";
            case 'VI':
                return "https://th.bing.com/th/id/OIP.VKZP_3q7BlSl279QfJx1BwHaEK?pid=ImgDet&rs=1";
            case 'AIRCEL':
                return "https://th.bing.com/th/id/R.31f979db479319bda69051ba2f86c5e2?rik=AQhmrkVH1wxetA&riu=http%3a%2f%2f4.bp.blogspot.com%2f-aEu_Y9121Aw%2fTkKgwHoKsEI%2fAAAAAAAAANo%2f8C5E6ZmCQhk%2fs1600%2fbsnl-logo.jpg&ehk=FCk6Bw5BSpHxCmlk9%2bh%2buq6S7V3mrkf9MyMf7MpwKHU%3d&risl=&pid=ImgRaw&r=0";
            case 'JIO':
                return "https://th.bing.com/th/id/R.776aa2f1afb01390f66557a3a73b4768?rik=wqpUSPG6RBkrgw&riu=http%3a%2f%2fwww.odishaage.com%2fwp-content%2fuploads%2f2021%2f07%2fJio-Logo.jpeg&ehk=81x0FXXSD3tWQKBHSPP8RcmPeBJyPy1T2yD4NPM2Ty8%3d&risl=&pid=ImgRaw&r=0";
            default:
                return "";
        }
    }
    return (
        <>
            <img height={50} width={60} src={getProviderImage()} alt={`${props.provider.type} image`} />
        </>
    );
}
