function embedRelations(rows, relConfig, data){
    for (const row of rows) {
        for (const cfg of relConfig.embed) {
            // Filter the junction records for this parent
            const links = data[cfg.from]
                .filter(j => j[cfg.localKey] === row.id);

            // Map junction items to full foreign records
            row[cfg.embedAs] = links.map(link =>
                data[cfg.targetCollection]
                    .find(f => f.id === link[cfg.foreignKey])
            );
        }
    }
    return rows;
}

const relations = {
    patients: {
        embed: [
            {
                from: "patients_diagnosis",
                localKey: "patient_id",
                foreignKey: "diagnosis_id",
                targetCollection: "diagnosis",
                embedAs: "diagnoses"
            }
        ]
    },
    hospitals: {
        embed: [
            {
                from: "hospitals_wards",
                localKey: "hospital_id",
                foreignKey: "ward_id",
                targetCollection: "wards",
                embedAs: "wards"
            }
        ]
    }
};

export { embedRelations, relations };