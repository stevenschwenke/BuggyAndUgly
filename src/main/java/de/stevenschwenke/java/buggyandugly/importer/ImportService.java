package de.stevenschwenke.java.buggyandugly.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class ImportService extends ImportCommonImpl {

    public ImportMetadata readImportMetaData(String filePath) {
        final AtomicReference<ImportMetadata> resultReference = new AtomicReference<>();
        Path path = Paths.get(filePath);

        try (Stream<String> stream = Files.lines(path)) {

            stream.filter(line -> line.startsWith(METADATA_PREFIX)).forEach(line -> {

                String[] importEntry = line.split(";");
                if (ImporterTypeEnum.IMPORT_PRODUCTION.getName().equals(importEntry[METADATA_INDEX_PRODUCTION_PRODUCED].trim())) {
                    ImportMetadata metadata;
                    if (resultReference.get() == null) {
                        metadata = new ImportMetadata();
                    } else {
                        metadata = resultReference.get();
                    }

                    metadata.setFilename(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()));
                    metadata.setArchive(false);
                    try {
                        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                        metadata.setCreationDate(Common.convertStringToDate(attr.creationTime().toString()));
                    } catch (IOException e) {
                        throw new ImporterException("Attributes from file could not be read.");
                    }
                    metadata.setCountry(importEntry[METADATA_INDEX_COUNTRY].replace(METADATA_PREFIX, "").trim());
                    metadata.setType(importEntry[METADATA_INDEX_TYPE].trim());

                    if (metadata.getSomeInternalNumber() == null || metadata.getSomeInternalNumber().equals("")) {
                        metadata.setSomeInternalNumber("");
                    } else {
                        metadata.setSomeInternalNumber(metadata.getSomeInternalNumber() + ", ");
                    }
                    if (!importEntry[METADATA_INDEX_SOME_INTERNAL_NUMBER].trim().equals("")) {
                        metadata.setSomeInternalNumber(metadata.getSomeInternalNumber() + importEntry[METADATA_INDEX_SOME_INTERNAL_NUMBER] + " ");
                    }
                    if (!importEntry[METADATA_INDEX_SOME_INTERNAL_NUMBER_2].trim().equals("")) {
                        metadata.setSomeInternalNumber(metadata.getSomeInternalNumber() + importEntry[METADATA_INDEX_SOME_INTERNAL_NUMBER_2]);
                    }

                    Date periodFrom = Common.convertYearStringToDate(importEntry[METADATA_INDEX_PERIOD_FROM]);
                    Date periodUntil = Common.convertYearStringToDate(importEntry[METADATA_INDEX_PERIOD_UNTIL]);

                    if (metadata.getPeriodFrom() == null)
                        metadata.setPeriodFrom(periodFrom);
                    if (metadata.getPeriodUntil() == null)
                        metadata.setPeriodUntil(periodUntil);

                    if (metadata.getPeriodFrom().after(periodFrom))
                        metadata.setPeriodFrom(periodFrom);
                    if (metadata.getPeriodUntil().before(periodUntil))
                        metadata.setPeriodUntil(periodUntil);
                    resultReference.set(metadata);
                }
            });
        } catch (IOException e) {
            throw new ImporterException("File does not exist or could not be read.");
        }
        return resultReference.get();
    }
}
