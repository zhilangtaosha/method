conversionStage = {
    $project : 1,
    from: 1,
    to: 1,
    amount: 1,
    timeStamp: {
        $convert : {
            input: "$timeStamp",
            to: "date",
            onError: {
                $convert: ["could not convert", {$toString: "$timeStamp"}, "to type date"]
            },
            onNull: "Missing timeStamp"

        }
    }

};

filterStage = {
    $match: {
        timeStamp: {"$type" : "date"}
    }
};

calcStage = {
    $group: {
        _id: {amount: "$from", year: {$year: "$timestamp"}, month: {$month: "$timestamp"}},
        sum: {$sum: "$amount"},
        count: {$sum: 1}
    }
}

/**
load("aggregate.js")
db.transfer.aggregate([conversionStage, filterStage, calcStage])

*/
